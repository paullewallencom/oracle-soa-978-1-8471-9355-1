package _leaverequest._form._war;

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.text.*;
import oracle.bpel.services.workflow.client.IWorkflowServiceClient;
import oracle.bpel.services.workflow.client.WorkflowServiceClientFactory;
import oracle.bpel.services.workflow.query.ITaskQueryService;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.task.model.IdentityType;
import oracle.bpel.services.workflow.worklist.display.Resource;
import oracle.bpel.services.workflow.worklist.display.ResourceKeyConstants;
import oracle.bpel.services.workflow.worklist.display.DOPostService;
import oracle.bpel.services.workflow.worklist.api.util.WorklistUtil;
import oracle.bpel.services.workflow.worklist.servlet.Constants;


public class _LeaveRequest extends com.orionserver.http.OrionHttpJspPage {


  // ** Begin Declarations


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
    _LeaveRequest page = this;
    ServletConfig config = pageContext.getServletConfig();

    try {


      out.write(__oracle_jsp_text[0]);
      out.write(__oracle_jsp_text[1]);
      out.write(__oracle_jsp_text[2]);
      
          
            try
            {    
            String taskId = request.getParameter(Constants.WORKLIST_TASKID_PARAMETER_NAME);
            String strTaskVersion = request.getParameter(Constants.WORKLIST_TASK_VERSION_PARAMETER_NAME);
            String contextId = request.getParameter(Constants.WORKLIST_CONTEXT_PARAMETER_NAME);
            String applicationName =
                              request.getParameter(Constants.APPLICATION_NAME);
      
            
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
            // get the TaskId and use above object
            SimpleDateFormat dfshort = new SimpleDateFormat( "MM/dd/yy" );
            SimpleDateFormat dflong = new SimpleDateFormat( "MM/dd/yy hh:mm a" );
            
            //add task in session attribute so that region jsp like
            //default which will be in same application don't have to query the
            //object again
            session.setAttribute(Constants.SESSION_CURRENT_TASK_OBJECT, task);
          
      out.write(__oracle_jsp_text[3]);
      out.print(Resource.getDisplayString(ResourceKeyConstants.BROWSERTITLE_KEY, 
                     request.getLocale()));
      out.write(__oracle_jsp_text[4]);
      
                  if(WorklistUtil.isTaskUpdatable(task,taskVersion))
                  {
                
      out.write(__oracle_jsp_text[5]);
      out.print( Resource.getDisplayString(ResourceKeyConstants.LABEL_SAVEL_BUTTON_NAME, request.getLocale()));
      out.write(__oracle_jsp_text[6]);
      
                  }
                
      out.write(__oracle_jsp_text[7]);
      {
        String __url=OracleJspRuntime.toStr("/Header1.jsp");
        // Include 
        pageContext.include( __url,false);
        if (pageContext.getAttribute(OracleJspRuntime.JSP_REQUEST_REDIRECTED, PageContext.REQUEST_SCOPE) != null) return;
      }

      out.write(__oracle_jsp_text[8]);
      {
        String __url=OracleJspRuntime.toStr("/payload-body.jsp");
        // Include 
        pageContext.include( __url,false);
        if (pageContext.getAttribute(OracleJspRuntime.JSP_REQUEST_REDIRECTED, PageContext.REQUEST_SCOPE) != null) return;
      }

      out.write(__oracle_jsp_text[9]);
      {
        String __url=OracleJspRuntime.toStr("/Footer1.jsp");
        // Include 
        pageContext.include( __url,false);
        if (pageContext.getAttribute(OracleJspRuntime.JSP_REQUEST_REDIRECTED, PageContext.REQUEST_SCOPE) != null) return;
      }

      out.write(__oracle_jsp_text[10]);
      
              }
              catch(Exception exc)
              {
                 throw exc;
              }
            
      out.write(__oracle_jsp_text[11]);

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
  private static final char __oracle_jsp_text[][]=new char[12][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "  <html>\r\n  ".toCharArray();
    __oracle_jsp_text[1] = 
    "\r\n  <!-- BEGIN_ADF_TEMPLATE -->\r\n  ".toCharArray();
    __oracle_jsp_text[2] = 
    "\r\n\r\n    \r\n    ".toCharArray();
    __oracle_jsp_text[3] = 
    " \r\n   <title>".toCharArray();
    __oracle_jsp_text[4] = 
    "</title>\r\n    <script type=\"text/javascript\">\r\n       \r\n       function isNameExist(keyNames, name)\r\n       {\r\n         var exist = \"false\";\r\n         for (keyName in keyNames)\r\n         {\r\n           if(trim(keyNames[keyName]) == trim(name))\r\n           {\r\n             exist = \"true\";\r\n           }\r\n         }\r\n         return exist;\r\n       }\r\n       \r\n       function generateSingleUpdate()\r\n       {\r\n         //var changed = false;\r\n         var keyNames = new Array(0);\r\n         var htmlForm = \"\";\r\n         htmlForm = htmlForm + '<form name=\"GLOBAL_UPDATE\" method=\"POST\" action=\"WFTaskUpdate\">';\r\n         htmlForm = htmlForm + '<input type=\"HIDDEN\" name=\"UpdateAll\" value=\"UpdateAll\"/>';\r\n         for(i = 0 ; i < document.forms.length ; i++)\r\n         {\r\n            var formObj = document.forms[i];\r\n            var actionName = formObj.action;\r\n            if(\r\n               (trim(actionName) == 'WFTaskUpdate' ||\r\n                actionName.match('/WFTaskUpdate') != 'null')\r\n                &&\r\n               trim(formObj.name) != 'PayloadJSPXML')\r\n            {\r\n              \r\n              for( j = 0 ; j < formObj.elements.length ; j++)\r\n              {\r\n                 var elemObj = formObj.elements[j];\r\n                 var name = elemObj.name;\r\n                 if(trim(elemObj.type) != \"submit\")\r\n                 {\r\n                   if(isNameExist(keyNames,name) == \"false\")\r\n                   {\r\n                     keyNames[keyNames.length] = name;\r\n                     htmlForm = htmlForm + '<input type=\"HIDDEN\" name=\"' + name + '\" value=\"\"/>';\r\n                   }\r\n                }\r\n              }           \r\n            }\r\n         }\r\n         htmlForm = htmlForm + '<\\/form>';\r\n         document.write(htmlForm);\r\n      }\r\n      \r\n      /**\r\n        Function to set the values from other form to global_update form\r\n       */\r\n      function setElementValue(form,name,value)\r\n      {\r\n        var elemObjArray = form.elements;\r\n        for(i = 0 ; i < elemObjArray.length ; i++)\r\n        {\r\n          var name1 = elemObjArray[i].name;\r\n          if(trim(name1) == trim(name))\r\n          {\r\n            elemObjArray[i].value = value;\r\n            return;\r\n          }\r\n        }\r\n      }\r\n      \r\n      /**\r\n        Function to set the call update form\r\n       */\r\n      function callUpdate()\r\n      {\r\n        var globalUpdateForm = document.forms['GLOBAL_UPDATE'];\r\n        for(i = 0 ; i < document.forms.length ; i++)\r\n         {\r\n            var formObj = document.forms[i];\r\n            var actionName = formObj.action;\r\n            var formName = formObj.name;\r\n            if(trim(formName) != 'GLOBAL_UPDATE' &&\r\n               trim(formName) != 'PayloadJSPXML' )\r\n            {\r\n              if(trim(actionName) == 'WFTaskUpdate' ||\r\n                 actionName.match('/WFTaskUpdate') != 'null')\r\n              {\r\n                for( j = 0 ; j < formObj.elements.length ; j++)\r\n                {\r\n                   var elemObj = formObj.elements[j];\r\n                   var name = elemObj.name;\r\n                   var value = elemObj.value;\r\n                   //setElementValue(globalUpdateForm,name,value)\r\n                   if(globalUpdateForm.elements[name] != null)\r\n                   {\r\n                     globalUpdateForm.elements[name].value = value;\r\n                   }\r\n                 }\r\n               }\r\n             }\r\n         }\r\n        globalUpdateForm.submit();\r\n      }\r\n      \r\n    function trim( str )\r\n    {\r\n        if( str == null )\r\n            return null;\r\n    \r\n        var start = 0;\r\n        for( ; start < str.length; start++ )\r\n        {\r\n            if( str.charAt( start ) != ' ' )\r\n                break;\r\n        }\r\n        if( start == str.length )\r\n        {\r\n            // Entire string is empty\r\n            //\r\n            return \"\";\r\n        }\r\n    \r\n        var stop = str.length - 1;\r\n        for( ; stop >= 0; stop-- )\r\n        {\r\n            if( str.charAt( stop ) != ' ' )\r\n                break;\r\n        }\r\n        return str.substring( start, stop + 1 );\r\n    }\r\n\r\n    </script>\r\n\r\n\r\n    <body>\r\n      <table width=\"100%\" align=\"CENTER\">\r\n\t<tr>\r\n        <td align=\"RIGHT\">\r\n          ".toCharArray();
    __oracle_jsp_text[5] = 
    "\r\n              <input type=\"SUBMIT\" \r\n                name=\"Update\"\r\n                value=\"".toCharArray();
    __oracle_jsp_text[6] = 
    "\" \r\n                OnClick=\"callUpdate()\"/>\r\n          ".toCharArray();
    __oracle_jsp_text[7] = 
    "\r\n        </td>\r\n       </tr>\r\n       <tr>\r\n        <td>\r\n         <table border=\"1\" width=\"100%\" align=\"CENTER\">\r\n          <tr><td>\r\n          ".toCharArray();
    __oracle_jsp_text[8] = 
    "\r\n          </td></tr>\r\n         </table>\r\n        </td>\r\n       </tr>\r\n        <tr>\r\n        <td>\r\n        <table border=\"1\" width=\"100%\" align=\"CENTER\">\r\n          <tr><td>\r\n          ".toCharArray();
    __oracle_jsp_text[9] = 
    "\r\n           </td></tr>\r\n         </table>\r\n        </td>\r\n       </tr>\r\n       <tr>\r\n        <td>\r\n         <table border=\"1\" width=\"100%\" align=\"CENTER\">\r\n          <tr><td>\r\n          ".toCharArray();
    __oracle_jsp_text[10] = 
    "\r\n           </td></tr>\r\n         </table>\r\n        </td>\r\n       </tr>\r\n      </table>\r\n      <script type=\"text/javascript\">\r\n        generateSingleUpdate();\r\n      </script>\r\n      ".toCharArray();
    __oracle_jsp_text[11] = 
    "\r\n    </body>\r\n  <!-- END_ADF_TEMPLATE -->\r\n  </html>\r\n".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
