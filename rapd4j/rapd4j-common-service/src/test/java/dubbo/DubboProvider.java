package dubbo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * 
 * @project:rapd4j-common-service
 * @class:DubboProvider
 * @description：
 * @author:xuzn
 * @date:2017-2-8 上午9:40:21
 * @modify:
 * @version:
 * 
 */
public class DubboProvider {

	private static final Log log = LogFactory.getLog(DubboProvider.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath:spring/dubbo-config-provider-test.xml");
			context.start();
		} catch (Exception e) {
			log.error("== DubboProvider context start error:", e);
		}
		synchronized (DubboProvider.class) {
			while (true) {
				try {
					DubboProvider.class.wait();
				} catch (InterruptedException e) {
					log.error("== synchronized error:", e);
				}
			}
		}
	}

}