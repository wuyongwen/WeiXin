package com.qq.weixin.mp.aes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class WXBizMsgCryptTest {
	String encodingAesKey = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";
	String token = "pamtest";
	String timestamp = "1409304348";
	String nonce = "xxxxxx";
	String appId = "wxb11529c136998cb6";
	String replyMsg = "我是中文abcd123";
	String xmlFormat = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
	String afterAesEncrypt = "jn1L23DB+6ELqJ+6bruv21Y6MD7KeIfP82D6gU39rmkgczbWwt5+3bnyg5K55bgVtVzd832WzZGMhkP72vVOfg==";
	String randomStr = "aaaabbbbccccdddd";

	String replyMsg2 = "<xml><ToUserName><![CDATA[oia2Tj我是中文jewbmiOUlr6X-1crbLOvLw]]></ToUserName><FromUserName><![CDATA[gh_7f083739789a]]></FromUserName><CreateTime>1407743423</CreateTime><MsgType><![CDATA[video]]></MsgType><Video><MediaId><![CDATA[eYJ1MbwPRJtOvIEabaxHs7TX2D-HV71s79GUxqdUkjm6Gs2Ed1KF3ulAOA9H1xG0]]></MediaId><Title><![CDATA[testCallBackReplyVideo]]></Title><Description><![CDATA[testCallBackReplyVideo]]></Description></Video></xml>";
	String afterAesEncrypt2 = "jn1L23DB+6ELqJ+6bruv23M2GmYfkv0xBh2h+XTBOKVKcgDFHle6gqcZ1cZrk3e1qjPQ1F4RsLWzQRG9udbKWesxlkupqcEcW7ZQweImX9+wLMa0GaUzpkycA8+IamDBxn5loLgZpnS7fVAbExOkK5DYHBmv5tptA9tklE/fTIILHR8HLXa5nQvFb3tYPKAlHF3rtTeayNf0QuM+UW/wM9enGIDIJHF7CLHiDNAYxr+r+OrJCmPQyTy8cVWlu9iSvOHPT/77bZqJucQHQ04sq7KZI27OcqpQNSto2OdHCoTccjggX5Z9Mma0nMJBU+jLKJ38YB1fBIz+vBzsYjrTmFQ44YfeEuZ+xRTQwr92vhA9OxchWVINGC50qE/6lmkwWTwGX9wtQpsJKhP+oS7rvTY8+VdzETdfakjkwQ5/Xka042OlUb1/slTwo4RscuQ+RdxSGvDahxAJ6+EAjLt9d8igHngxIbf6YyqqROxuxqIeIch3CssH/LqRs+iAcILvApYZckqmA7FNERspKA5f8GoJ9sv8xmGvZ9Yrf57cExWtnX8aCMMaBropU/1k+hKP5LVdzbWCG0hGwx/dQudYR/eXp3P0XxjlFiy+9DMlaFExWUZQDajPkdPrEeOwofJb";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNormal() throws ParserConfigurationException, SAXException, IOException {
		try {
			WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
			String afterEncrpt = pc.encryptMsg(replyMsg, timestamp, nonce);
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(afterEncrpt);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);

			Element root = document.getDocumentElement();
			NodeList nodelist1 = root.getElementsByTagName("Encrypt");
			NodeList nodelist2 = root.getElementsByTagName("MsgSignature");

			String encrypt = nodelist1.item(0).getTextContent();
			String msgSignature = nodelist2.item(0).getTextContent();
			String fromXML = String.format(xmlFormat, encrypt);
			// 第三方收到公众号平台发送的消息
			
			String afterDecrpt = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
			assertEquals(replyMsg, afterDecrpt);
		} catch (AesException e) {
			fail("正常流程，怎么就抛出异常了？？？？？？");
		}
	}

	@Test
	public void testAesEncrypt() {
		try {
			WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
			assertEquals(afterAesEncrypt, pc.encrypt(randomStr, replyMsg));
		} catch (AesException e) {
			e.printStackTrace();
			fail("no异常");
		}
	}

	@Test
	public void testAesEncrypt2() {
		try {
			WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
			assertEquals(afterAesEncrypt2, pc.encrypt(randomStr, replyMsg2));

		} catch (AesException e) {
			e.printStackTrace();
			fail("no异常");
		}
	}

	@Test
	public void testIllegalAesKey() {
		try {
			new WXBizMsgCrypt(token, "abcde", appId);
		} catch (AesException e) {
			assertEquals(AesException.IllegalAesKey, e.getCode());
			return;
		}
		fail("错误流程不抛出异常？？？");
	}
	
	@Test
	public void testValidateSignatureError() throws ParserConfigurationException, SAXException,
			IOException {
		try {
			WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
			String afterEncrpt = pc.encryptMsg(replyMsg, timestamp, nonce);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(afterEncrpt);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);

			Element root = document.getDocumentElement();
			NodeList nodelist1 = root.getElementsByTagName("Encrypt");

			String encrypt = nodelist1.item(0).getTextContent();
			String fromXML = String.format(xmlFormat, encrypt);
			pc.decryptMsg("12345", timestamp, nonce, fromXML); // 这里签名错误
		} catch (AesException e) {
			assertEquals(AesException.ValidateSignatureError, e.getCode());
			return;
		}
		fail("错误流程不抛出异常？？？");
	}

	@Test
	public void testVerifyUrl() throws AesException {
		WXBizMsgCrypt wxcpt = new WXBizMsgCrypt("QDG6eK",
				"jWmYm7qr5nMoAUwZRjGtBxmz3KA1tkAj3ykkR6q2B2C", "wx5823bf96d3bd56c7");
		String verifyMsgSig = "5c45ff5e21c57e6ad56bac8758b79b1d9ac89fd3";
		String timeStamp = "1409659589";
		String nonce = "263014780";
		String echoStr = "P9nAzCzyDtyTWESHep1vC5X9xho/qYX3Zpb4yKa9SKld1DsH3Iyt3tP3zNdtp+4RPcs8TgAE7OaBO+FZXvnaqQ==";
		wxcpt.verifyUrl(verifyMsgSig, timeStamp, nonce, echoStr);
		// 只要不抛出异常就好
	}
	//@Test
	public void test() throws AesException{
		String timetamp = "1456907505";
		String encrypt_type = "aes";
		String nonce = "1231152662";
		String xmlContent = "<xml>"+
	    "<AppId><![CDATA[wx7613bea5e4d18127]]></AppId>"+
	    "<Encrypt><![CDATA[MyFm7jGFq7JwVR6TRPYsk7vFpDrO8Q51ZBzXVNbWBRDw7dhkFEE9QNIHtV6Oe2I27SjWoZ1cTx+s4YJOJM0wR5w6wC63T6LQ44lg51jpkjYW37FmS0VOnAvPzN2JzZWBtfvBqezh1/E06fNHiQJQR4hkrpjsocDOcxjAg8UJ3GiXQukGpJE71S6frCAfgMFKLzXb63TLpCbO8DFLPDnIa9nAX3eyv+HBNw7Ckih1cwlejq3pF6kr9oFLIgNsPB3AfhKSwwgPQjBy6NUzQaJIDOwrvNA6fgWMvL7Qpf7wP6So9fsXD/ILSGrjrcFEi7jmspThO8JY2/ERfU2NXzNv7l/El5KtzPappwY7r2X2Gk35Fhap6ao1AKbJqFiuHAdKIBmUesfrtGACz2mZ03U71qL2wsDZMU92WOtwhTPqhNuGNeESmHVhVfFUJ4Obb94lymX74ronMkV+Vw9oeirLMw==]]></Encrypt>"+
	    "</xml>"; // 第三方平台中 微信后台推送的加密的component_verify_ticket
		String msg_signature = "a25c1dc7796b63db9472abc040f9549f258abacf";
		String signature = "5ff423fff7c316e95f0a06cfe799222e50c0a379";
		String token = "wxtoken";
		String appId = "wx7613bea5e4d18127";
		String encodingAesKey = "KgwVOifMrDO0tQNbzdoeWP6abSF2MIN97UuSlZDLrAI";
		WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
		String decrptMsg = pc.decryptMsg(msg_signature, timetamp, nonce, xmlContent);
		System.out.println(decrptMsg);
	}
	@Test
	public void testEcryptMsg() throws AesException{
		String xmlContent = "<xml>"+
			"<Encrypt><![CDATA[tgXIn7z9imMUj+F0URED+Qtl9QnDLW/vCq8jrWe/tAW2nal7jDHWBm3hW9/MP7TWYETVd6VFPY3aNVwqKPCLi783kBMNDF0TyGCAI9v5xFyGET5nK+fNemOq/foGfDNkZXqlVukWjvuPZn7qUlSF46Qb1pUbIrgsOiEaRhJHWu15SuUWzFKFoCKTKc4MLXN7N+M6hwVdnvclDPM/jSO6evvlbQVR/56+u9TrVUlYYmaiIGqF6wvJ0XFiYKXfL8IkLcNp2NU9DyZ5C14t2QLrJFjb3LKk+qUCq1CLw7mHzOnzEOuTb/g1kOJv7cz61E294OP16s5MaQ+5XO1LjYSYIDTckKxLnDeUZTLPJH4G+1IbPmpl3DDajlDT3Lsz7xH+D4pzHDn1vP5hn96AZHt/LHsA3L5y928iQw6wkjzXWC5eaepra96Wwz0+pV5vZg2FBiEQfSv9c37BWvOHVQZHszqQO3awg1e2CkSt56Yu4BIUdYbWZk8efdjVx21vwin86MNZ9QlXPJSbc5eFqo+O2TmOkx1VSGV4iAc7k5Jh5n3WAMSQawVr69FRYfBqaETRpbIxjubBKeoS5WZ1C4mvmg6J0/sPut2Gbj3Ah/3NzZiOPemlkh6gUGgNWRvL5Ijq]]></Encrypt>"+
			"<MsgSignature><![CDATA[48753b98b03d7d734cfbaa1f659aa6be0c8da1ae]]></MsgSignature>"+
			"<TimeStamp>1457515354</TimeStamp>"+
			"<Nonce><![CDATA[843317629]]></Nonce>"+
			"</xml>";
		WXBizMsgCrypt pc = new WXBizMsgCrypt("wxtoken", "KgwVOifMrDO0tQNbzdoeWP6abSF2MIN97UuSlZDLrAI", "wx7613bea5e4d18127");
		String decrptMsg = pc.decryptMsg("e4f7b51bb9946cd57103ad816781e2c901c3fe3d", "1457520226", "76784791", xmlContent);
		System.out.println(decrptMsg);
	}
}
