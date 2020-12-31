  <!-- BPM Worklist Application; Human Workflow Application -->
  <!-- JSP for displaying Details page -->


<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="oracle.bpel.services.workflow.task.model.Task"%>
<%@ page import="oracle.bpel.services.workflow.worklist.servlet.Constants"%>
<%@ page import="oracle.bpel.services.workflow.worklist.api.payload.FormUtil"%>
<%@ page import="oracle.bpel.services.workflow.worklist.api.payload.Field"%>
<%@ page import="oracle.bpel.services.workflow.worklist.api.payload.PayloadFormGenerator"%>
<%@ page import="oracle.bpel.services.workflow.worklist.api.payload.Form"%>
<%@ page import="oracle.bpel.services.workflow.client.IWorkflowServiceClient"%>
<%@ page import="oracle.bpel.services.workflow.client.WorkflowServiceClientFactory"%>
<%@ page import="oracle.bpel.services.workflow.query.ITaskQueryService"%>
<%@ page import="oracle.bpel.services.workflow.verification.IWorkflowContext"%>
<%@ page import="org.w3c.dom.*"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%
  try {
%>
<% 
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
  %>
<!-- USER CAN MODIFY THE FOLLOWING CODE -->
<div id="htmlView"
     style="padding:10px;padding-top:0px;padding-bottom:0px;display:block">
  <br/><br/>
   
  <form id="PayloadJSPHTML" name="PayloadJSPHTML"
        action="<%=Constants.UPDATE_SERVLET_NAME%>" method="post"
        onsubmit="return validateData(this)">
    <!-- print required params -->
    <input type="hidden" name="tableOperationAdd" value=""/>
    <input type="hidden" name="tableOperationRemove" value=""/>
    <input type="hidden"
           name="<%=Constants.WFTASKPAYLOAD_UPDATE_BUTTON_KEY_NAME%>" value=""/>
    <%
          Iterator iter = requiredParamNames.iterator();
          while (iter.hasNext()) {
            String paramName = (String) iter.next();
            String paramValue = (String) requiredParams.get(paramName);
        %>
    <input type="hidden" name="<%=paramName%>" value="<%=paramValue%>"/>
    <%
          }     
        %>
    <!-- print form -->
    <table border="0" cellpadding="0" cellspacing="3">
      <%
          Field thisField = null;
          String thisValue = "";
          String thisDisabled = "";
          
          
					%>
						</table>
				<hr align="left" width="80%"/>
				<table cellpadding="0" cellspacing="3">
				<tr><td colspan="3" class="payloadSectionTitle" id="ns2_cl_item">Item</td></tr>

					<%
					%>
						<%=getField(payload, form, context, thisDisabled, locale, task, "Category Code", "/ns0:task/ns0:payload/ns1:checkSuspectItem/ns2:item/ns2:categoryCode", "string")%>
<%
%>
					<%
					%>
						<%=getField(payload, form, context, thisDisabled, locale, task, "Title", "/ns0:task/ns0:payload/ns1:checkSuspectItem/ns2:item/ns2:title", "string")%>
<%
%>
					<%
					%>
						<%=getField(payload, form, context, thisDisabled, locale, task, "Description", "/ns0:task/ns0:payload/ns1:checkSuspectItem/ns2:item/ns2:description", "string")%>
<%
%>
					<%
					%>
						<%=getField(payload, form, context, thisDisabled, locale, task, "Condition", "/ns0:task/ns0:payload/ns1:checkSuspectItem/ns2:item/ns2:condition", "string")%>
<%
%>
					<%
					%>
						</table>
				<hr align="left" width="80%"/>
				<table cellpadding="0" cellspacing="3">
				<tr><td colspan="3" class="payloadSectionTitle" id="ns1_cl_checkSuspectItem">Check Suspect Item</td></tr>

					<%
					%>
						<%=getField(payload, form, context, thisDisabled, locale, task, "Reason Code", "/ns0:task/ns0:payload/ns1:checkSuspectItem/reasonCode", "string")%>
<%
%>
					<%
					%>
						<%=getField(payload, form, context, thisDisabled, locale, task, "Reason Description", "/ns0:task/ns0:payload/ns1:checkSuspectItem/reasonDescription", "string")%>
<%
%>
					<%


        %>
    </table>
  </form>
</div>
<% 
  }
  catch (Exception e) {
    out.flush();
    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                      e.toString());
  } 
%>
<%!
     private String getXMLMappingFileURL( HttpServletRequest request) 
     {
       String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
%>
