  <!-- BPM Worklist Express / TaskDetails.jsp -->  
  <%@ page contentType="text/html;charset=UTF-8"%>
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
                   oracle.bpel.services.workflow.worklist.servlet.Constants,
                   oracle.bpel.services.workflow.worklist.api.payload.PayloadConstant;"%>

    
    <%
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
      
      //no need to use Notm to get the task
      Task task = (Task)session.getAttribute(Constants.SESSION_CURRENT_TASK_OBJECT);
      
      IWorkflowServiceClient  wfSvcClient =
                        WorkflowServiceClientFactory.getWorkflowServiceClient(
                                 WorkflowServiceClientFactory.JAVA_CLIENT);
      ITaskQueryService queryService =  wfSvcClient.getTaskQueryService();

      IWorkflowContext context = queryService.getWorkflowContext(contextId);
 
      
      if(task == null)
      {
         if(taskVersion == 0)
         {
           task =  queryService.getTaskDetailsById(context, taskId);
         } 
         else
         {
           task = queryService.getTaskVersionDetails(context,taskId,taskVersion);
         }
      }
      Locale locale = context.getLocale();
      
      // get the TaskId and use above object
      SimpleDateFormat dfshort = new SimpleDateFormat( "MM/dd/yy" );
      SimpleDateFormat dflong = new SimpleDateFormat( "MM/dd/yy hh:mm a" );
 
       String nextPage = request.getParameter(Constants.WORKLIST_NEXT_PAGE_PARAMETER_NAME);
       String loginPage = request.getParameter(Constants.WORKLIST_LOGIN_PAGE_PARAMETER_NAME);
      String errorPage = request.getParameter(Constants.WORKLIST_ERROR_PAGE_PARAMETER_NAME);

      String styleSheetURL =  request.getParameter(Constants.STYLE_SHEET_URL);

      
    %>

    <style type="text/css">
      @import url(headerfooter.css);
      <%
         if(styleSheetURL != null && !styleSheetURL.trim().equals(""))
         {
           out.println("@import url(" + styleSheetURL + ");");
         }
      %>
    </style>
 
    <!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
    <!-- Display Task Header (Important) Attributes -->
    <!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
    <!-- Task Details table (Read Only Attributes)-->
     <form name="PRIORITYUPDATE" style="display:inline;" method="POST" action="WFTaskUpdate">
     
     <input type="HIDDEN"  name="<%=Constants.WORKLIST_TASKID_PARAMETER_NAME%>" value="<%=taskId%>"/>
     <input type="HIDDEN"  name="<%=Constants.WORKLIST_CONTEXT_PARAMETER_NAME%>" value="<%=contextId%>"/>
     <input type="HIDDEN"  name="<%=Constants.WFTASKNOTM_KEY_NAME%>" value="<%=task.getSystemAttributes().getNumberOfTimesModified()%>"/>

      <input type="HIDDEN"  name="<%=Constants.WORKLIST_NEXT_PAGE_PARAMETER_NAME%>" value="<%=nextPage%>"/>
      <input type="HIDDEN"  name="<%=Constants.WORKLIST_LOGIN_PAGE_PARAMETER_NAME%>" value="<%=loginPage%>"/>
      <input type="HIDDEN"  name="<%=Constants.WORKLIST_ERROR_PAGE_PARAMETER_NAME%>" value="<%=errorPage%>"/>
    
     <table width="100%" align="center" class="HeaderPane"  >
        <tr style="height:20px">
           <!-- First Column Label & Data -->
           <td width="12%" class="bold" align="right">
             <%=Resource.getDisplayString(ResourceKeyConstants.TASKNUMBER_KEY,locale)%>:
           </td>
           <td width="13%" align="left">
             <%=task.getSystemAttributes().getTaskNumber()%>
           </td>          
 
           <!-- Second Column Label & Data -->
           <td width="15%" class="bold" align="right">
             <%=Resource.getDisplayString(ResourceKeyConstants.TITLE_KEY,locale)%>:
           </td>
           <td width="25%" align="left">
             <%= task.getTitle() %>
           </td>          

           <!-- Third Column Label & Data -->
            <td width="15%" align="right" class="bold">
             <%=Resource.getDisplayString(ResourceKeyConstants.IDENTIFICATION_KEY_KEY,locale)%>
            </td>
            <td width="20%" align="left" >
              <% 
                  String identKey = task.getIdentificationKey();
                  if(identKey == null) identKey = "";
                  out.println(identKey);
               %>
            </td>

        </tr>

        <tr style="height:20px">
             <td align="right" class="bold">
                 <%=Resource.getDisplayString(ResourceKeyConstants.STATE_KEY,locale)%>:
             </td>
             <td align="left">
                <%=Resource.getDisplayString("LABEL_TASK_STATE_"+task.getSystemAttributes().getState(),locale)%>
             </td>
             
             <td align="right" class="bold">
                <%=Resource.getDisplayString(ResourceKeyConstants.CREATEDDATE_KEY,locale)%>:
             </td>
             <td align="left">
              <%
                Calendar createDate = task.getSystemAttributes().getCreatedDate();
                if(createDate != null)
                {
                  out.println(dfshort.format(createDate.getTime()));
                }
              %>
             </td>
             <td align="right" class="bold" style="word-space:nowrap;">
                <%=Resource.getDisplayString(ResourceKeyConstants.CREATOR_KEY,locale)%>:
             </td>
             <td align="left" >
               <%
                  String creator = task.getCreator();
                  if(creator == null) creator = "";
                  out.println(creator);
               %>
             </td>
        </tr>     
        <tr style="height:20px">
             <td align="right" class="bold">
                 <%=Resource.getDisplayString(ResourceKeyConstants.OUTCOME_KEY,locale)%>:
             </td>
             <td  align="left">
               <% 
                  String outCome = task.getSystemAttributes().getOutcome();
                  if(outCome == null) outCome = "";
                  out.println(outCome);
               %>
             </td>
             
             <td  align="right" class="bold">
                <%=Resource.getDisplayString(ResourceKeyConstants.UPDATEDDATE_KEY,locale)%>:
             </td>
             <td  align="left">
              <%
                Calendar updateDate = task.getSystemAttributes().getUpdatedDate();
                if(updateDate != null)
                {
                  out.println(dflong.format(updateDate.getTime()));
                }
              %>
             </td>
             <td  align="right" class="bold" style="word-space:nowrap;">
                <%=Resource.getDisplayString(ResourceKeyConstants.ACQUIREDBY_KEY,locale)%>:
             </td>
             <td align="left" >
                 <%
                    String aqBy = task.getSystemAttributes().getAcquiredBy();
                    if(aqBy == null) aqBy = "";
                    out.println(aqBy);
                 %>
             </td>
        </tr>
      
        <tr style="height:20px">
             <td align="right" class="bold">
                <%=Resource.getDisplayString(ResourceKeyConstants.PRIORITY_KEY,locale)%>:
             </td>
             <td align="left" >
              
               <%
                  int priority = task.getPriority();
                  
                  //TO DO add more checks status/permission
                  if(taskVersion == 0)
                  {
                    %>
                      <select name="<%=Constants.WFTASKPRIORITY_KEY_NAME%>">
                      <option value="<%=priority%>"><%=priority%></option>
                    <%
                       if(priority != 1)
                       {
                         out.println("<option value=\"1\">1</option>");
                       }                
                       if(priority != 2)
                       {
                         out.println("<option value=\"2\">2</option>");
                       }               
                       if(priority != 3)
                       {
                         out.println("<option value=\"3\">3</option>");
                       }       
                       if(priority != 4)
                       {
                         out.println("<option value=\"4\">4</option>");
                       }       
                       if(priority != 5)
                       {
                         out.println("<option value=\"5\">5</option>");
                       }       
                    %>
                    </select>
                 <%
                   }
                   else
                   {
                     out.println("" + priority);
                   }
                 %>
             </td>
             <td align="right" class="bold" >
                <%=Resource.getDisplayString(ResourceKeyConstants.EXPIRATIONDATE_KEY,locale)%>:
             </td>
             <td  align="left" >
               <%
                Calendar expDate = task.getSystemAttributes().getExpirationDate();
                if(expDate != null)
                {
                  out.print(dflong.format(expDate.getTime()));
                }
              %>
             </td>
             <td  align="right" class="bold" style="white-space:nowrap;">
                <%=Resource.getDisplayString(ResourceKeyConstants.ASSIGNEES_KEY,locale)%>:
             </td>
             <td  align="left" >
               <%
                 String assignees = null;
                 List assigneeUsers = task.getSystemAttributes().getAssigneeUsers();
                 
                 for(int i = 0 ; i < assigneeUsers.size() ; i++)
                 {
                   IdentityType type = (IdentityType)assigneeUsers.get(i);
                   if(assignees == null)
                   {
                     assignees =type.getId() + "(U)";
                   }
                   else
                   {
                     assignees = assignees + "," + type.getId() + "(U)";
                   }
                 }
                 
                 List assigneeGroups = task.getSystemAttributes().getAssigneeGroups();
                 for(int i = 0 ; i < assigneeGroups.size() ; i++)
                 {
                   IdentityType type = (IdentityType)assigneeGroups.get(i);
                   if(assignees == null)
                   {
                     assignees =type.getId() + "(U)";
                   }
                   else
                   {
                     assignees = assignees + "," + type.getId() + "(U)";
                   }
                 }
                 out.println(assignees);
               %>
             </td>
        </tr> 
       </table>
      </form> 
