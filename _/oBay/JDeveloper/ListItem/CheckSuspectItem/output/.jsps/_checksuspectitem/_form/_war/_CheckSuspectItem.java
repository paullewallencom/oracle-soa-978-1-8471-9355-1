package _checksuspectitem._form._war;

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.text.*;
import oracle.bpel.services.workflow.client.IWorkflowServiceClient;
import oracle.bpel.services.workflow.client.WorkflowServiceClientFactory;
import oracle.bpel.services.workflow.query.ITaskQueryService;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.worklist.display.Resource;
import oracle.bpel.services.workflow.worklist.display.ResourceKeyConstants;
import oracle.bpel.services.workflow.worklist.api.util.WorklistUtil;
import oracle.bpel.services.workflow.worklist.servlet.Constants;


public class _CheckSuspectItem extends com.orionserver.http.OrionHttpJspPage {


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
    _CheckSuspectItem page = this;
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
    "  <html>\n  ".toCharArray();
    __oracle_jsp_text[1] = 
    "\n  <!-- BEGIN_ADF_TEMPLATE -->\n  ".toCharArray();
    __oracle_jsp_text[2] = 
    "\n\n    \n    ".toCharArray();
    __oracle_jsp_text[3] = 
    " \n   <title>".toCharArray();
    __oracle_jsp_text[4] = 
    "</title>\n    <script type=\"text/javascript\">\n       \n       function isNameExist(keyNames, name)\n       {\n         var exist = \"false\";\n         for (keyName in keyNames)\n         {\n           if(trim(keyNames[keyName]) == trim(name))\n           {\n             exist = \"true\";\n           }\n         }\n         return exist;\n       }\n       \n       function generateSingleUpdate()\n       {\n         //var changed = false;\n         var keyNames = new Array(0);\n         var htmlForm = \"\";\n         htmlForm = htmlForm + '<form name=\"GLOBAL_UPDATE\" method=\"POST\" action=\"WFTaskUpdate\">';\n         htmlForm = htmlForm + '<input type=\"HIDDEN\" name=\"UpdateAll\" value=\"UpdateAll\"/>';\n         for(i = 0 ; i < document.forms.length ; i++)\n         {\n            var formObj = document.forms[i];\n            var actionName = formObj.action;\n            if(\n               (trim(actionName) == 'WFTaskUpdate' ||\n                actionName.match('/WFTaskUpdate') != 'null')\n                &&\n               trim(formObj.name) != 'PayloadJSPXML')\n            {\n              \n              for( j = 0 ; j < formObj.elements.length ; j++)\n              {\n                 var elemObj = formObj.elements[j];\n                 var name = elemObj.name;\n                 if(trim(elemObj.type) != \"submit\")\n                 {\n                   if(isNameExist(keyNames,name) == \"false\")\n                   {\n                     keyNames[keyNames.length] = name;\n                     htmlForm = htmlForm + '<input type=\"HIDDEN\" name=\"' + name + '\" value=\"\"/>';\n                   }\n                }\n              }           \n            }\n         }\n         htmlForm = htmlForm + '<\\/form>';\n         document.write(htmlForm);\n      }\n      \n      /**\n        Function to set the values from other form to global_update form\n       */\n      function setElementValue(form,name,value)\n      {\n        var elemObjArray = form.elements;\n        for(i = 0 ; i < elemObjArray.length ; i++)\n        {\n          var name1 = elemObjArray[i].name;\n          if(trim(name1) == trim(name))\n          {\n            elemObjArray[i].value = value;\n            return;\n          }\n        }\n      }\n      \n      /**\n        Function to set the call update form\n       */\n      function callUpdate()\n      {\n        var globalUpdateForm = document.forms['GLOBAL_UPDATE'];\n        for(i = 0 ; i < document.forms.length ; i++)\n         {\n            var formObj = document.forms[i];\n            var actionName = formObj.action;\n            var formName = formObj.name;\n            if(trim(formName) != 'GLOBAL_UPDATE' &&\n               trim(formName) != 'PayloadJSPXML' )\n            {\n              if(trim(actionName) == 'WFTaskUpdate' ||\n                 actionName.match('/WFTaskUpdate') != 'null')\n              {\n                for( j = 0 ; j < formObj.elements.length ; j++)\n                {\n                   var elemObj = formObj.elements[j];\n                   var name = elemObj.name;\n                   var value = elemObj.value;\n                   //setElementValue(globalUpdateForm,name,value)\n                   if(globalUpdateForm.elements[name] != null)\n                   {\n                     globalUpdateForm.elements[name].value = value;\n                   }\n                 }\n               }\n             }\n         }\n        globalUpdateForm.submit();\n      }\n      \n    function trim( str )\n    {\n        if( str == null )\n            return null;\n    \n        var start = 0;\n        for( ; start < str.length; start++ )\n        {\n            if( str.charAt( start ) != ' ' )\n                break;\n        }\n        if( start == str.length )\n        {\n            // Entire string is empty\n            //\n            return \"\";\n        }\n    \n        var stop = str.length - 1;\n        for( ; stop >= 0; stop-- )\n        {\n            if( str.charAt( stop ) != ' ' )\n                break;\n        }\n        return str.substring( start, stop + 1 );\n    }\n\n    </script>\n\n\n    <body>\n      <table width=\"100%\" align=\"CENTER\">\n\t<tr>\n        <td align=\"RIGHT\">\n          ".toCharArray();
    __oracle_jsp_text[5] = 
    "\n              <input type=\"SUBMIT\" \n                name=\"Update\"\n                value=\"".toCharArray();
    __oracle_jsp_text[6] = 
    "\" \n                OnClick=\"callUpdate()\"/>\n          ".toCharArray();
    __oracle_jsp_text[7] = 
    "\n        </td>\n       </tr>\n       <tr>\n        <td>\n         <table border=\"1\" width=\"100%\" align=\"CENTER\">\n          <tr><td>\n          ".toCharArray();
    __oracle_jsp_text[8] = 
    "\n          </td></tr>\n         </table>\n        </td>\n       </tr>\n        <tr>\n        <td>\n        <table border=\"1\" width=\"100%\" align=\"CENTER\">\n          <tr><td>\n          ".toCharArray();
    __oracle_jsp_text[9] = 
    "\n           </td></tr>\n         </table>\n        </td>\n       </tr>\n       <tr>\n        <td>\n         <table border=\"1\" width=\"100%\" align=\"CENTER\">\n          <tr><td>\n          ".toCharArray();
    __oracle_jsp_text[10] = 
    "\n           </td></tr>\n         </table>\n        </td>\n       </tr>\n      </table>\n      <script type=\"text/javascript\">\n        generateSingleUpdate();\n      </script>\n      ".toCharArray();
    __oracle_jsp_text[11] = 
    "\n    </body>\n  <!-- END_ADF_TEMPLATE -->\n  </html>\n".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
