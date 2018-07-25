package util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class UriComponentTest {

  private static final Logger logger = LoggerFactory.getLogger(UriComponentTest.class);

  @Test
  public void testURI1() throws Exception {
    UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .path("/article/read")
            .queryParam("articleNo", 10)
            .queryParam("perPageNum", 20)
            .build();

    logger.info("/article/read?articleNo=10&perPageNum=20");
    logger.info(uriComponents.toString());
  }

  @Test
  public void testURI2() throws Exception {
    UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .path("/{module}/{page}")
            .queryParam("articleNo", 10)
            .queryParam("perPageNum", 20)
            .build()
            .expand("article", "read")
            .encode();

    logger.info("/article/read?articleNo=10&perPageNum=20");
    logger.info(uriComponents.toString());
  }

}
