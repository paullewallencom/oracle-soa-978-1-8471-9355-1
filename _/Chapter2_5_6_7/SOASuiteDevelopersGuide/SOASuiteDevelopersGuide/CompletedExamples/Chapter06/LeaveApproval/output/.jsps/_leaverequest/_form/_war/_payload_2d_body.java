package _leaverequest._form._war;

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.text.*;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.worklist.servlet.Constants;
import oracle.bpel.services.workflow.worklist.api.payload.FormUtil;
import oracle.bpel.services.workflow.worklist.api.payload.Field;
import oracle.bpel.services.workflow.worklist.api.payload.PayloadFormGenerator;
import oracle.bpel.services.workflow.worklist.api.payload.Form;
import oracle.bpel.services.workflow.worklist.api.payload.PayloadConstant;
import oracle.bpel.services.workflow.client.IWorkflowServiceClient;
import oracle.bpel.services.workflow.client.WorkflowServiceClientFactory;
import oracle.bpel.services.workflow.query.ITaskQueryService;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import oracle.bpel.services.workflow.worklist.display.*;
import org.w3c.dom.*;


public class _payload_2d_body extends com.orionserver.http.OrionHttpJspPage {


  // ** Begin Declarations


     private String getXMLMappingFileURL( HttpServletRequest request) 
     {
       String url = "http://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() +"/";
       
       url = url + "payload-body.xml"; 
       return url;
     }
     
    /*
      Return code for a field display. Use this method to display non-table field.
    */
    private String getField(Element payload, Form form, IWorkflowContext context,
         String thisDisabled, Locale locale, Task task, String displayName, String xpath, String datatype) throws Exception
     {
         Field thisField = null;
         thisField = form.getField(xpath);
         if (thisField == null || thisField.isEditable()) {
                                                thisDisabled = "";
                                        }
                                        else {
                                                thisDisabled = "disabled";
                                        }
       return "<tr><th align=\"left\">"
       + displayName + "<font class=\"payloadAsterick\">&nbsp;*</font></th>"
       + " <td align=\"left\"><input name=\""
       + PayloadFormGenerator.constructName(xpath)
       + "\" type=\"text\" value=\""
       + PayloadFormGenerator.selectNodeValue(payload, xpath,
         form.getNamespaceMap(), datatype, locale) + "\" "
       + thisDisabled
       + " dataType=\"" + datatype + "\"></input></td> "
       + "<input name=\""
       + PayloadFormGenerator.constructDisplayName(xpath)
       + "\" type=\"hidden\" value=\""
       + FormUtil.getElementDisplayName(xpath,
         form,locale,task,context) + "\">"
       + "</input><td align=\"left\" class=\"payloadDataType\">"
       + FormUtil.getDatatypeLocale(datatype,locale)
       + "</td><input name=\""
       + PayloadFormGenerator.constructDataTypeName(xpath)
       + "\" type=\"hidden\" value=\"" + datatype + "\"></input></tr>";
     }

    /*
      Return code for a field display. Use this method to display table field.
    */
    private String getTableField(Element payload, Form form, 
       String displayName, String xpath, 
       String inloopXPath, String datatype) throws Exception
    {
        Field thisField = null;        
        thisField = form.getField(xpath);
        String thisDisabled = "";
	if (thisField == null || thisField.isEditable())
	   thisDisabled = "";
	else
           thisDisabled = "disabled";
        return "<td align=\"left\"><input name=\""
        + PayloadFormGenerator.constructName(inloopXPath)
        + "\" value=\""
        + PayloadFormGenerator.selectNodeValue(payload, inloopXPath, form.getNamespaceMap())
        + "\" dataType=\""
        + datatype + "\"" + thisDisabled
        + "></input></td>";

    }

  // ** End Declarations

  public void _jspService(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException, ServletException {

    response.setContentType( "text/html;charset=UTF-8");
    /* set up the intrinsic variables using the pageContext goober:
    ** session = HttpSession
    ** application = ServletContext
    ** out = JspWriter
    ** page = this
    ** config = ServletConfig
    ** all session/app beans declared in globals.jsa
    */
    PageContext pageContext = JspFactory.getDefaultFactory().getPageContext( this, request, response, null, true, JspWriter.DEFAULT_BUFFER, true);
    // Note: this is not emitted if the session directive == false
    HttpSession session = pageContext.getSession();
    int __jsp_tag_starteval;
    ServletContext application = pageContext.getServletContext();
    JspWriter out = pageContext.getOut();
    _payload_2d_body page = this;
    ServletConfig config = pageContext.getServletConfig();

    try {


      out.write(__oracle_jsp_text[0]);
      out.write(__oracle_jsp_text[1]);
      out.write(__oracle_jsp_text[2]);
      out.write(__oracle_jsp_text[3]);
      out.write(__oracle_jsp_text[4]);
      out.write(__oracle_jsp_text[5]);
      out.write(__oracle_jsp_text[6]);
      out.write(__oracle_jsp_text[7]);
      out.write(__oracle_jsp_text[8]);
      out.write(__oracle_jsp_text[9]);
      out.write(__oracle_jsp_text[10]);
      
        try {
      
      out.write(__oracle_jsp_text[11]);
       
          /**
          * this block gets the payload object from the task object
          * Note: contextKey, taskId annd task have been null checked 
          *       before calling this jsp, no null checking needs to be done here
          * USER SHOULD NOT MODIFY THIS BLOCK
          */
          String taskId = request.getParameter(Constants.WORKLIST_TASKID_PARAMETER_NAME);
          String strTaskVersion = request.getParameter(Constants.WORKLIST_TASK_VERSION_PARAMETER_NAME);
          String contextId = request.getParameter(Constants.WORKLIST_CONTEXT_PARAMETER_NAME);
          
          int taskVersion = 0;
          // incase strTaskVersion is null means user wants latest version
          // from WFTask table
          // else it wants from the WFTaskHistory table
          if(strTaskVersion != null && !strTaskVersion.trim().equals(""))
          {
            try
            {
              taskVersion = Integer.parseInt(strTaskVersion);
            }
            catch(NumberFormatException exc)
            {
              //TO DO throw the exception
              taskVersion = 1;
            }
          }
         
          IWorkflowServiceClient  wfSvcClient =
                              WorkflowServiceClientFactory.getWorkflowServiceClient(
                                       WorkflowServiceClientFactory.JAVA_CLIENT);
          ITaskQueryService queryService =  wfSvcClient.getTaskQueryService();
          IWorkflowContext context = queryService.getWorkflowContext(contextId);
       
          Task task = null;
          
          if(taskVersion == 0)
          {
             task =  queryService.getTaskDetailsById(context, taskId);
          } 
          else
          {
            task = queryService.getTaskVersionDetails(context,taskId,taskVersion);
          }
          
          //get the locale from the context
          Locale locale = context.getLocale();
          String contextPath = request.getContextPath();
          
          String xmlURL = getXMLMappingFileURL(request);
          Form form = PayloadFormGenerator.getMappingForm(task,xmlURL);
          Element payload = (Element) task.getPayloadAsElement();
      
          //TO DO add login page
          
          String nextPage 
               = request.getParameter(Constants.WORKLIST_NEXT_PAGE_PARAMETER_NAME);
          String loginPage 
             = request.getParameter(Constants.WORKLIST_LOGIN_PAGE_PARAMETER_NAME);
          String errorPage 
             = request.getParameter(Constants.WORKLIST_ERROR_PAGE_PARAMETER_NAME);
        
          Map requiredParams 
             = PayloadFormGenerator.getRequiredFormParameters(form.getNamespaceMap(), 
                                                              task, 
                                                              context, 
                                                              nextPage, 
                                                              loginPage, 
                                                              errorPage);
          Set requiredParamNames = requiredParams.keySet();
        
          boolean canUpdate = PayloadFormGenerator.canUpdate(task,taskVersion);
          boolean showXmlView = form.showXmlView();
          boolean xmlEditable = form.isXmlEditable();
          String xmlDisabledStr = xmlEditable ? "" : "DISABLED";
        
      out.write(__oracle_jsp_text[12]);
      out.print(Constants.UPDATE_SERVLET_NAME);
      out.write(__oracle_jsp_text[13]);
      out.print(Constants.WFTASKPAYLOAD_UPDATE_BUTTON_KEY_NAME);
      out.write(__oracle_jsp_text[14]);
      
                Iterator iter = requiredParamNames.iterator();
                while (iter.hasNext()) {
                  String paramName = (String) iter.next();
                  String paramValue = (String) requiredParams.get(paramName);
              
      out.write(__oracle_jsp_text[15]);
      out.print(paramName);
      out.write(__oracle_jsp_text[16]);
      out.print(paramValue);
      out.write(__oracle_jsp_text[17]);
      
                }     
              
      out.write(__oracle_jsp_text[18]);
      
                Field thisField = null;
                String thisValue = "";
                String thisDisabled = "";
                
                
      					
      out.write(__oracle_jsp_text[19]);
      
      					
      out.write(__oracle_jsp_text[20]);
      out.print(getField(payload, form, context, thisDisabled, locale, task, "Employee Id", "/ns0:task/ns0:payload/ns1:leaveRequest/ns1:employeeId", "string"));
      out.write(__oracle_jsp_text[21]);
      
      
      out.write(__oracle_jsp_text[22]);
      
      					
      out.write(__oracle_jsp_text[23]);
      out.print(getField(payload, form, context, thisDisabled, locale, task, "Full Name", "/ns0:task/ns0:payload/ns1:leaveRequest/ns1:fullName", "string"));
      out.write(__oracle_jsp_text[24]);
      
      
      out.write(__oracle_jsp_text[25]);
      
      					
      out.write(__oracle_jsp_text[26]);
      out.print(getField(payload, form, context, thisDisabled, locale, task, "Start Date", "/ns0:task/ns0:payload/ns1:leaveRequest/ns1:startDate", "date"));
      out.write(__oracle_jsp_text[27]);
      
      
      out.write(__oracle_jsp_text[28]);
      
      					
      out.write(__oracle_jsp_text[29]);
      out.print(getField(payload, form, context, thisDisabled, locale, task, "End Date", "/ns0:task/ns0:payload/ns1:leaveRequest/ns1:endDate", "date"));
      out.write(__oracle_jsp_text[30]);
      
      
      out.write(__oracle_jsp_text[31]);
      
      					
      out.write(__oracle_jsp_text[32]);
      out.print(getField(payload, form, context, thisDisabled, locale, task, "Leave Type", "/ns0:task/ns0:payload/ns1:leaveRequest/ns1:leaveType", "string"));
      out.write(__oracle_jsp_text[33]);
      
      
      out.write(__oracle_jsp_text[34]);
      
      					
      out.write(__oracle_jsp_text[35]);
      out.print(getField(payload, form, context, thisDisabled, locale, task, "Leave Reason", "/ns0:task/ns0:payload/ns1:leaveRequest/ns1:leaveReason", "string"));
      out.write(__oracle_jsp_text[36]);
      
      
      out.write(__oracle_jsp_text[37]);
      
      					
      out.write(__oracle_jsp_text[38]);
      out.print(getField(payload, form, context, thisDisabled, locale, task, "Request Status", "/ns0:task/ns0:payload/ns1:leaveRequest/ns1:requestStatus", "string"));
      out.write(__oracle_jsp_text[39]);
      
      
      out.write(__oracle_jsp_text[40]);
      
      
      
              
      out.write(__oracle_jsp_text[41]);
       
        }
        catch (Exception e) {
          out.flush();
          response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                            e.toString());
        } 
      
      out.write(__oracle_jsp_text[42]);
      out.write(__oracle_jsp_text[43]);

    }
    catch (Throwable e) {
      if (!(e instanceof javax.servlet.jsp.SkipPageException)){
        try {
          if (out != null) out.clear();
        }
        catch (Exception clearException) {
        }
        pageContext.handlePageException(e);
      }
    }
    finally {
      OracleJspRuntime.extraHandlePCFinally(pageContext, true);
      JspFactory.getDefaultFactory().releasePageContext(pageContext);
    }

  }
  private static final char __oracle_jsp_text[][]=new char[44][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "  <!-- BPM Worklist Application; Human Workflow Application -->\n  <!-- JSP for displaying Details page -->\n\n\n".toCharArray();
    __oracle_jsp_text[1] = 
    "\n".toCharArray();
    __oracle_jsp_text[2] = 
    "\n".toCharArray();
    __oracle_jsp_text[3] = 
    "\n".toCharArray();
    __oracle_jsp_text[4] = 
    "\n".toCharArray();
    __oracle_jsp_text[5] = 
    "\n".toCharArray();
    __oracle_jsp_text[6] = 
    "\n".toCharArray();
    __oracle_jsp_text[7] = 
    "\n".toCharArray();
    __oracle_jsp_text[8] = 
    "\n".toCharArray();
    __oracle_jsp_text[9] = 
    "\n".toCharArray();
    __oracle_jsp_text[10] = 
    "\n".toCharArray();
    __oracle_jsp_text[11] = 
    "\n".toCharArray();
    __oracle_jsp_text[12] = 
    "\n<!-- USER CAN MODIFY THE FOLLOWING CODE -->\n<div id=\"htmlView\"\n     style=\"padding:10px;padding-top:0px;padding-bottom:0px;display:block\">\n  <br/><br/>\n   \n  <form id=\"PayloadJSPHTML\" name=\"PayloadJSPHTML\"\n        action=\"".toCharArray();
    __oracle_jsp_text[13] = 
    "\" method=\"post\"\n        onsubmit=\"return validateData(this)\">\n    <!-- print required params -->\n    <input type=\"hidden\" name=\"tableOperationAdd\" value=\"\"/>\n    <input type=\"hidden\" name=\"tableOperationRemove\" value=\"\"/>\n    <input type=\"hidden\"\n           name=\"".toCharArray();
    __oracle_jsp_text[14] = 
    "\" value=\"\"/>\n    ".toCharArray();
    __oracle_jsp_text[15] = 
    "\n    <input type=\"hidden\" name=\"".toCharArray();
    __oracle_jsp_text[16] = 
    "\" value=\"".toCharArray();
    __oracle_jsp_text[17] = 
    "\"/>\n    ".toCharArray();
    __oracle_jsp_text[18] = 
    "\n    <!-- print form -->\n    <table border=\"0\" cellpadding=\"0\" cellspacing=\"3\">\n      ".toCharArray();
    __oracle_jsp_text[19] = 
    "\n\t\t\t\t\t\t</table>\n\t\t\t\t<hr align=\"left\" width=\"80%\"/>\n\t\t\t\t<table cellpadding=\"0\" cellspacing=\"3\">\n\t\t\t\t<tr><td colspan=\"3\" class=\"payloadSectionTitle\" id=\"ns1_cl_leaveRequest\">Leave Request</td></tr>\n\n\t\t\t\t\t".toCharArray();
    __oracle_jsp_text[20] = 
    "\n\t\t\t\t\t\t".toCharArray();
    __oracle_jsp_text[21] = 
    "\n".toCharArray();
    __oracle_jsp_text[22] = 
    "\n\t\t\t\t\t".toCharArray();
    __oracle_jsp_text[23] = 
    "\n\t\t\t\t\t\t".toCharArray();
    __oracle_jsp_text[24] = 
    "\n".toCharArray();
    __oracle_jsp_text[25] = 
    "\n\t\t\t\t\t".toCharArray();
    __oracle_jsp_text[26] = 
    "\n\t\t\t\t\t\t".toCharArray();
    __oracle_jsp_text[27] = 
    "\n".toCharArray();
    __oracle_jsp_text[28] = 
    "\n\t\t\t\t\t".toCharArray();
    __oracle_jsp_text[29] = 
    "\n\t\t\t\t\t\t".toCharArray();
    __oracle_jsp_text[30] = 
    "\n".toCharArray();
    __oracle_jsp_text[31] = 
    "\n\t\t\t\t\t".toCharArray();
    __oracle_jsp_text[32] = 
    "\n\t\t\t\t\t\t".toCharArray();
    __oracle_jsp_text[33] = 
    "\n".toCharArray();
    __oracle_jsp_text[34] = 
    "\n\t\t\t\t\t".toCharArray();
    __oracle_jsp_text[35] = 
    "\n\t\t\t\t\t\t".toCharArray();
    __oracle_jsp_text[36] = 
    "\n".toCharArray();
    __oracle_jsp_text[37] = 
    "\n\t\t\t\t\t".toCharArray();
    __oracle_jsp_text[38] = 
    "\n\t\t\t\t\t\t".toCharArray();
    __oracle_jsp_text[39] = 
    "\n".toCharArray();
    __oracle_jsp_text[40] = 
    "\n\t\t\t\t\t".toCharArray();
    __oracle_jsp_text[41] = 
    "\n    </table>\n  </form>\n</div>\n".toCharArray();
    __oracle_jsp_text[42] = 
    "\n".toCharArray();
    __oracle_jsp_text[43] = 
    "\n".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
