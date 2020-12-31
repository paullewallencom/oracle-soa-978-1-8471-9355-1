  <html>
  <%@ page contentType="text/html;charset=UTF-8"%>
  <!-- BEGIN_ADF_TEMPLATE -->
  <%@ page import="java.util.*,
                   java.net.URLEncoder,
                   java.io.UnsupportedEncodingException, 
                   java.text.*,
                   oracle.bpel.services.workflow.client.IWorkflowServiceClient,
                   oracle.bpel.services.workflow.client.WorkflowServiceClientFactory,
                   oracle.bpel.services.workflow.query.ITaskQueryService,
                   oracle.bpel.services.workflow.verification.IWorkflowContext,
                   oracle.bpel.services.workflow.task.model.Task,
                   oracle.bpel.services.workflow.task.model.IdentityType,
                   
                   oracle.bpel.services.workflow.worklist.display.Resource,
                   oracle.bpel.services.workflow.worklist.display.ResourceKeyConstants,
                   oracle.bpel.services.workflow.worklist.display.DOPostService,
                   oracle.bpel.services.workflow.worklist.api.util.WorklistUtil,
                   oracle.bpel.services.workflow.worklist.servlet.Constants"%>

    
    <%
    
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
    %> 
   <title><%=Resource.getDisplayString(ResourceKeyConstants.BROWSERTITLE_KEY, 
                     request.getLocale())%></title>
    <script type="text/javascript">
       
       function isNameExist(keyNames, name)
       {
         var exist = "false";
         for (keyName in keyNames)
         {
           if(trim(keyNames[keyName]) == trim(name))
           {
             exist = "true";
           }
         }
         return exist;
       }
       
       function generateSingleUpdate()
       {
         //var changed = false;
         var keyNames = new Array(0);
         var htmlForm = "";
         htmlForm = htmlForm + '<form name="GLOBAL_UPDATE" method="POST" action="WFTaskUpdate">';
         htmlForm = htmlForm + '<input type="HIDDEN" name="UpdateAll" value="UpdateAll"/>';
         for(i = 0 ; i < document.forms.length ; i++)
         {
            var formObj = document.forms[i];
            var actionName = formObj.action;
            if(
               (trim(actionName) == 'WFTaskUpdate' ||
                actionName.match('/WFTaskUpdate') != 'null')
                &&
               trim(formObj.name) != 'PayloadJSPXML')
            {
              
              for( j = 0 ; j < formObj.elements.length ; j++)
              {
                 var elemObj = formObj.elements[j];
                 var name = elemObj.name;
                 if(trim(elemObj.type) != "submit")
                 {
                   if(isNameExist(keyNames,name) == "false")
                   {
                     keyNames[keyNames.length] = name;
                     htmlForm = htmlForm + '<input type="HIDDEN" name="' + name + '" value=""/>';
                   }
                }
              }           
            }
         }
         htmlForm = htmlForm + '<\/form>';
         document.write(htmlForm);
      }
      
      /**
        Function to set the values from other form to global_update form
       */
      function setElementValue(form,name,value)
      {
        var elemObjArray = form.elements;
        for(i = 0 ; i < elemObjArray.length ; i++)
        {
          var name1 = elemObjArray[i].name;
          if(trim(name1) == trim(name))
          {
            elemObjArray[i].value = value;
            return;
          }
        }
      }
      
      /**
        Function to set the call update form
       */
      function callUpdate()
      {
        var globalUpdateForm = document.forms['GLOBAL_UPDATE'];
        for(i = 0 ; i < document.forms.length ; i++)
         {
            var formObj = document.forms[i];
            var actionName = formObj.action;
            var formName = formObj.name;
            if(trim(formName) != 'GLOBAL_UPDATE' &&
               trim(formName) != 'PayloadJSPXML' )
            {
              if(trim(actionName) == 'WFTaskUpdate' ||
                 actionName.match('/WFTaskUpdate') != 'null')
              {
                for( j = 0 ; j < formObj.elements.length ; j++)
                {
                   var elemObj = formObj.elements[j];
                   var name = elemObj.name;
                   var value = elemObj.value;
                   //setElementValue(globalUpdateForm,name,value)
                   if(globalUpdateForm.elements[name] != null)
                   {
                     globalUpdateForm.elements[name].value = value;
                   }
                 }
               }
             }
         }
        globalUpdateForm.submit();
      }
      
    function trim( str )
    {
        if( str == null )
            return null;
    
        var start = 0;
        for( ; start < str.length; start++ )
        {
            if( str.charAt( start ) != ' ' )
                break;
        }
        if( start == str.length )
        {
            // Entire string is empty
            //
            return "";
        }
    
        var stop = str.length - 1;
        for( ; stop >= 0; stop-- )
        {
            if( str.charAt( stop ) != ' ' )
                break;
        }
        return str.substring( start, stop + 1 );
    }

    </script>


    <body>
      <table width="100%" align="CENTER">
	<tr>
        <td align="RIGHT">
          <%
            if(WorklistUtil.isTaskUpdatable(task,taskVersion))
            {
          %>
              <input type="SUBMIT" 
                name="Update"
                value="<%= Resource.getDisplayString(ResourceKeyConstants.LABEL_SAVEL_BUTTON_NAME, request.getLocale())%>" 
                OnClick="callUpdate()"/>
          <%
            }
          %>
        </td>
       </tr>
       <tr>
        <td>
         <table border="1" width="100%" align="CENTER">
          <tr><td>
          <jsp:include page="/Header1.jsp" />
          </td></tr>
         </table>
        </td>
       </tr>
        <tr>
        <td>
        <table border="1" width="100%" align="CENTER">
          <tr><td>
          <jsp:include page="/payload-body.jsp" />
           </td></tr>
         </table>
        </td>
       </tr>
       <tr>
        <td>
         <table border="1" width="100%" align="CENTER">
          <tr><td>
          <jsp:include page="/Footer1.jsp" />
           </td></tr>
         </table>
        </td>
       </tr>
      </table>
      <script type="text/javascript">
        generateSingleUpdate();
      </script>
      <%
        }
        catch(Exception exc)
        {
           throw exc;
        }
      %>
    </body>
  <!-- END_ADF_TEMPLATE -->
  </html>
