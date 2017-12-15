package cn.chenxhcloud.batch.processor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import cn.chenxhcloud.batch.dto.AlipayTranDO;
import cn.chenxhcloud.batch.dto.MyPayTranDO;

/**
 * 
 * @author Administrator
 *
 */
@Component
public class AlipayItemProcessor implements ItemProcessor<AlipayTranDO, MyPayTranDO> {

	private static final Logger log = LoggerFactory.getLogger(AlipayItemProcessor.class);

	@Override
	public MyPayTranDO process(AlipayTranDO alipayTranDO) throws Exception {
		MyPayTranDO myPayTranDO = new MyPayTranDO();
		myPayTranDO.setTranId(alipayTranDO.getTranId());
		myPayTranDO.setChannel(alipayTranDO.getChannel());
		myPayTranDO.setTranType(alipayTranDO.getTranType());
		myPayTranDO.setCounterparty(alipayTranDO.getCounterparty());
		myPayTranDO.setGoods(alipayTranDO.getGoods());
		myPayTranDO.setAmount(alipayTranDO.getAmount());
		myPayTranDO.setIsDebitCredit(alipayTranDO.getIsDebitCredit());
		myPayTranDO.setState(alipayTranDO.getState());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(new Date());
		myPayTranDO.setTranDate(dateNowStr);
		myPayTranDO.setMerId("00000001");
		log.info(alipayTranDO.toString());
		return myPayTranDO;
	}
}