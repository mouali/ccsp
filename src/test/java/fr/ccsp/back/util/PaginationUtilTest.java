package fr.ccsp.back.util;

import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;

public class PaginationUtilTest {
	
	@Test
	public void test() throws URISyntaxException{
		
		Page pageTest = new PageTest();
		String baseUrl = "/api/plis";
		
		HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(pageTest, baseUrl);
		
		Assert.assertNotNull(httpHeaders);
		
	}
	
	public class PageTest implements Page {

		@Override
		public int getNumber() {
			return 10;
		}

		@Override
		public int getSize() {
			return 20;
		}

		@Override
		public int getNumberOfElements() {
			return 0;
		}

		@Override
		public List getContent() {
			return null;
		}

		@Override
		public boolean hasContent() {
			return false;
		}

		@Override
		public Sort getSort() {
			return null;
		}

		@Override
		public boolean isFirst() {
			return false;
		}

		@Override
		public boolean isLast() {
			return false;
		}

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public boolean hasPrevious() {
			return false;
		}

		@Override
		public Pageable nextPageable() {
			return null;
		}

		@Override
		public Pageable previousPageable() {
			return null;
		}

		@Override
		public Iterator iterator() {
			return null;
		}

		@Override
		public int getTotalPages() {
			return 0;
		}

		@Override
		public long getTotalElements() {
			return 0;
		}

		@Override
		public Page map(Converter converter) {
			return null;
		}
		
	}

}
