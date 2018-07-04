package com.spring.commons.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 컨트롤러에서 발생하는 Exception을 전문적으로 처리하는 클래스라는 것을 명시합니다.
 *
 * 어떤 예외가 발생했는지 브라우저에 로그를 출력함으로써 빠르게 예외나 에러가 발생한 원인을 찾을 수 있습니다.
 * 하지만, 실제 서비스할 때는 사용자가 웹을 사용하면서 예외를 마주치게 해서는 안되기 때문에,
 * 이 경우를 대비해 404, 500 에러 정도를 알려주는 적절한 페이지를 만들어주는 것이 바람직합니다.
 */
@ControllerAdvice
public class CommonExceptAdvice {

  private static final Logger logger = LoggerFactory.getLogger(CommonExceptAdvice.class);

  /**
   * @ExceptionHandler라는 어노테이션을 통해 적절한 타입의 Exception을 처리하도록 합니다.
   * 일반 컨트롤러 클래스와 다르게 파라미터로 Model 타입을 지원하지 않기 때문에
   * ModelAndView 타입을 직접 지정하는 형태로 사용해야 합니다.
   *
   * ModelAndView는 하나의 객체에 Model 데이터와 View의 처리를 동시에 할 수 있는 객체입니다.
   * 만약, 예외가 발생하면 발생한 예외 내용이 담긴 데이터를 exception에 담고,
   * common_error.jsp에 전달하게 됩니다.
   *
   * @param e
   * @return
   */
  @ExceptionHandler(Exception.class)
  public ModelAndView commonException(Exception e) {
    logger.info(e.toString());
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("exception", e);
    modelAndView.setViewName("/commons/common_error");

    return modelAndView;
  }

}
