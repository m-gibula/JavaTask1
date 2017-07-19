package org.javaee7.servlet.metadata.complete;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import org.javaee7.servlet.simple.AuthFilter;
import org.javaee7.servlet.simple.Paste;
import org.javaee7.servlet.simple.PasteServlet;
import org.javaee7.servlet.simple.SimpleServlet;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * @author Arun Gupta
 */
@RunWith(Arquillian.class)
public class ServletTest {

    WebClient webClient;
    @ArquillianResource
    private URL base;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addClass(PasteServlet.class).addClass(SimpleServlet.class).addClass(AuthFilter.class).addClass(Paste.class);
        return war;
    }

    @Before
    public void setup() throws IOException {
        webClient = new WebClient();
    }

    @Test
    public void doGet_request_formShouldExist() throws IOException {
        //given
        WebClient client = new WebClient();
        WebRequest requestSettings = new WebRequest(new URL(base+"add?pw=passwd"), HttpMethod.GET);
        //when
        HtmlPage page = client.getPage(requestSettings);
        //then
        assertTrue("Page should contain form",!page.getElementsByTagName("form").isEmpty());

    }


    @Test
    public void SimpleServletPasteServlet_doPost_ShouldBeSame() throws IOException {
        //given
        WebRequest requestSettings = new WebRequest(
                new URL(base+"add?pw=passwd"), HttpMethod.POST);

        Date data = new Date();
        Random gen = new Random();
        requestSettings.setRequestParameters(new ArrayList());
        String title = data.getTime()+"";
        String content = gen.nextInt()+"";
        //when
        requestSettings.getRequestParameters().add(new NameValuePair("title", title));
        requestSettings.getRequestParameters().add(new NameValuePair("content", content));
        HtmlPage page  = webClient.getPage(requestSettings);
        if(!page.getElementsByTagName("a").isEmpty()) {
            String pasteLink = page.getElementsByTagName("a").get(0).getAttribute("href");


            requestSettings = new WebRequest(
                    new URL(base + pasteLink), HttpMethod.GET);
            page = webClient.getPage(requestSettings);
            //then
            assertEquals("doGet to /paste should print concatenate parameters",title + content, page.getBody().getTextContent());

        }
        else{
            fail("doPost to /paste should print link to paste");
        }

    }
    @Test
    public void doGet_differentClients_outputShouldBeSame() throws IOException {
        WebRequest requestSettings = new WebRequest(
                new URL(base+"add?pw=passwd"), HttpMethod.POST);

        Date data = new Date();
        Random gen = new Random();
        requestSettings.setRequestParameters(new ArrayList());
        String title = data.getTime()+"";
        String content = gen.nextInt()+"";
        //when
        requestSettings.getRequestParameters().add(new NameValuePair("title", title));
        requestSettings.getRequestParameters().add(new NameValuePair("content", content));
        HtmlPage page  = webClient.getPage(requestSettings);
        if(!page.getElementsByTagName("a").isEmpty()) {
            String pasteLink = page.getElementsByTagName("a").get(0).getAttribute("href");


            requestSettings = new WebRequest(
                    new URL(base + pasteLink), HttpMethod.GET);
            WebClient wc = new WebClient();
            page = wc.getPage(requestSettings);
            //then
            assertEquals("doGet to /paste should print concatenate parameters",title + content, page.getBody().getTextContent());

        }
        else{
            fail("doPost to /paste should print link to paste");
        }
    }
    @Test
    public void filter_parameter_shouldExist() throws IOException {
        WebClient webClient = new WebClient();
        WebRequest requestSettings = new WebRequest(
                new URL(base+"add"), HttpMethod.GET);
        HtmlPage page  = webClient.getPage(requestSettings);
        assertEquals("Missing parameter message should be here","Missing password",page.getBody().getTextContent());
    }
    @Test
    public void filter_parameter_shouldBeValid() throws IOException {
        WebClient webClient = new WebClient();
        WebRequest requestSettings = new WebRequest(
                new URL(base+"add?pw=x"), HttpMethod.GET);
        HtmlPage page  = webClient.getPage(requestSettings);
        assertEquals("Wrong password message should be here","Wrong password",page.getBody().getTextContent());
    }







}