package com.bins.mockito.first;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.soap.SOAPException;

public class ASAPSoapMessageTest {
    /*
    ASOAPMessage testASAPSoapMessage= new ASOAPMessage();
    String testSOAPRequest = "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\"><s:Body xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema\"><LoginRequest xmlns=\"http://asap.schemas.tfn.thomsonreuters.com/Messages/Base/2010-03-01/\"/></s:Body></s:Envelope>";
    File testFile = new File("testFile.xml");
    String testSOAPAction = "http://fackSOAPAction/";
    String testWebService = "http://fackWebService/";
    String testReply = "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\"><s:Body><LoginResponse xmlns=\"http://asap.schemas.tfn.thomsonreuters.com/Messages/Base/2010-03-01/\"/></s:Body></s:Envelope>";
    MessageFactory mf;
    SOAPMessage SOAPRequestTest;
    SOAPMessage replySOAPRequestTest;*/
    
    public void testUp() throws SOAPException{
        /*mf = MessageFactory.newInstance();
        SOAPRequestTest = mf.createMessage();
        replySOAPRequestTest = mf.createMessage();*/
    }

    public void testGetSoapContent() throws IOException, SOAPException{
        /*
        String soapContentString = "";
            
        StreamSource prepMsg = new StreamSource(new StringReader(testSOAPRequest));
        SOAPPart sp = SOAPRequestTest.getSOAPPart();
        sp.setContent(prepMsg);
        soapContentString = testASAPSoapMessage.getSoapContent(SOAPRequestTest);
              
        assertTrue(soapContentString.equalsIgnoreCase(testSOAPRequest));*/
    }

    private void getSoapConfig() throws MalformedURLException {
//        String ns = "http://axisversion.sample";
//        String wsdlUrl = "http://127.0.0.1:8080/axis2/services/Version?wsdl";
//        //1����������(Service)
//        URL url = new URL(wsdlUrl);
//        QName sname = new QName(ns,"Version");
//        Service service = Service.create(url,sname);
//
//        //2������Dispatch
//        Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns,"VersionHttpSoap11Endpoint"),SOAPMessage.class,Service.Mode.MESSAGE);
//
//        //3������SOAPMessage
//        SOAPMessage msg = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL).createMessage();
//        SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
//        SOAPBody body = envelope.getBody();
//
//        //4������QName��ָ����Ϣ�д�������
//        QName ename = new QName(ns,"getVersion","axis");//<nn:add xmlns="xx"/>
//        SOAPBodyElement ele = body.addBodyElement(ename);
//        // ���ݲ���
////        ele.addChildElement("a").setValue("22");
////        ele.addChildElement("b").setValue("33");
//        msg.writeTo(System.out);
//        System.out.println("\n invoking.....");
//
//        //5��ͨ��Dispatch������Ϣ,�᷵����Ӧ��Ϣ
//        SOAPMessage response = dispatch.invoke(msg);
//        response.writeTo(System.out);
//        System.out.println();
//
//        //6����Ӧ��Ϣ����,����Ӧ����Ϣת��Ϊdom����
//        Document doc = response.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
//        String str = doc.getElementsByTagName("ns:return").item(0).getTextContent();
//        System.out.println(str);
    }
    
    public void testPrepareSOAPMessage() throws IOException{
        /*FileUtils.writeStringToFile(this.testFile,this.testSOAPRequest);
        assertTrue(testASAPSoapMessage.prepareSOAPMessage("testFile.xml").equalsIgnoreCase(testSOAPRequest)); */
    }
    
    public void testSendSOAPMessage() throws SOAPException, IOException{
        /*
        SOAPPart sp = replySOAPRequestTest.getSOAPPart();
        StreamSource prepMsg = new StreamSource(new StringReader(testReply));
        sp.setContent(prepMsg);
        
        SOAPConnection mockedSOAPConnection = mock(SOAPConnection.class);  
        when(mockedSOAPConnection.call(argThat(new IsSOAPMessage()), anyObject())).thenReturn(replySOAPRequestTest); 
        
        ASAPSoapMessage spyASAPSoapMessage = spy(new ASAPSoapMessage());
        when(spyASAPSoapMessage.getSoapConnection()).thenReturn(mockedSOAPConnection); 
        
        SOAPMessage replySOAPMessage= spyASAPSoapMessage.sendSOAPMessage(testSOAPRequest, testWebService, testSOAPAction);
        assertTrue(spyASAPSoapMessage.getSoapContent(replySOAPMessage).equalsIgnoreCase(testReply)); */
    }

    /*
    class IsSOAPMessage extends ArgumentMatcher<SOAPMessage> {
        public boolean matches(Object soapMessage) {
            return soapMessage instanceof SOAPMessage;
        }
    }

    class ASOAPMessage {

    }*/
}