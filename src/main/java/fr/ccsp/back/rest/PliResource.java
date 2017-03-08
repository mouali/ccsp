package fr.ccsp.back.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ccsp.back.domain.Pli;
import fr.ccsp.back.repository.PliRepository;
import fr.ccsp.back.util.HeaderUtil;
import fr.ccsp.back.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
@RestController
@RequestMapping("/api")
public class PliResource {

    private final Logger log = LoggerFactory.getLogger(PliResource.class);

    private static final String ENTITY_NAME = "pli";
        
    private final PliRepository pliRepository;

    public PliResource(PliRepository pliRepository) {
        this.pliRepository = pliRepository;
    }

    /**
     * POST  /plis : Create a new pli.
     *
     * @param pli the pli to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pli, or with status 400 (Bad Request) if the pli has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plis")
    public ResponseEntity<Pli> createPli(@Valid @RequestBody Pli pli) throws URISyntaxException {
        log.debug("REST request to save Pli : {}", pli);
        if (pli.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new pli cannot already have an ID")).body(null);
        }
        Pli result = pliRepository.save(pli);
        return ResponseEntity.created(new URI("/api/plis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plis : Updates an existing pli.
     *
     * @param pli the pli to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pli,
     * or with status 400 (Bad Request) if the pli is not valid,
     * or with status 500 (Internal Server Error) if the pli couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plis")
    public ResponseEntity<Pli> updatePli(@Valid @RequestBody Pli pli) throws URISyntaxException {
        log.debug("REST request to update Pli : {}", pli);
        if (pli.getId() == null) {
            return createPli(pli);
        }
        Pli result = pliRepository.save(pli);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pli.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plis : get all the plis.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of plis in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/plis")
    public ResponseEntity<List<Pli>> getAllPlis(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Plis");
        Page<Pli> page = pliRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plis");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /plis/:id : get the "id" pli.
     *
     * @param id the id of the pli to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pli, or with status 404 (Not Found)
     */
    @GetMapping("/plis/{id}")
    public Pli getPli(@PathVariable String id) {
        log.debug("REST request to get Pli : {}", id);
        Pli pli = pliRepository.findOne(id);
        return pli;
    }

    /**
     * DELETE  /plis/:id : delete the "id" pli.
     *
     * @param id the id of the pli to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plis/{id}")
    public ResponseEntity<Void> deletePli(@PathVariable String id) {
        log.debug("REST request to delete Pli : {}", id);
        pliRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
