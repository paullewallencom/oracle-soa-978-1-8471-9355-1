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
                   oracle.bpel.services.workflow.task.model.CommentType,
                   oracle.bpel.services.workflow.task.model.AttachmentType,
                   oracle.bpel.services.workflow.task.model.IdentityType,
                   oracle.bpel.services.workflow.worklist.display.Resource,
                   oracle.bpel.services.workflow.worklist.display.ResourceKeyConstants,
                   oracle.bpel.services.workflow.worklist.servlet.Constants,
                   oracle.bpel.services.workflow.worklist.api.util.WorklistUtil,
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


    <script>
    
       function trimString (str) 
       {
          while (str.charAt(0) == ' ')
            str = str.substring(1);
          while (str.charAt(str.length - 1) == ' ')
            str = str.substring(0, str.length - 1);
          return str;
       }


       function showUpdateCommentWindow()
       {
         var updateCommentTextArea = document.getElementById('UPDATE_COMMENT_TEXTAREA');
         //set the display to '' and visibility to ''
         updateCommentTextArea.style.display  = '';
         updateCommentTextArea.style.visibility  = '';
         
         var uploadFileAttachment = document.getElementById('UPLOAD_FILE_ATTACHMENT');
         var attachmentDisplay = uploadFileAttachment.style.display;
         //get the attachment display
         // if it none then set display to '' and visiblity to hidden
         if(attachmentDisplay != '')
         {
           uploadFileAttachment.style.visibility = 'hidden';
         }
         
       }
       function hideUpdateCommentWindow()
       {
         var updateCommentTextArea = document.getElementById('UPDATE_COMMENT_TEXTAREA');
         var uploadFileAttachment = document.getElementById('UPLOAD_FILE_ATTACHMENT');
         var hiddenFA = uploadFileAttachment.style.visibility;
         if(trimString(hiddenFA) != 'hidden')
         {
           updateCommentTextArea.style.visibility = 'hidden';
         }
         else
         {
           uploadFileAttachment.style.display  = 'none';
           uploadFileAttachment.style.visibility = 'hidden'
           updateCommentTextArea.style.display  = 'none';
           updateCommentTextArea.style.visibility = 'hidden'
         }
       }
       
       function showAddAttachmentWindow()
       {
         var updateCommentTextArea = document.getElementById('UPDATE_COMMENT_TEXTAREA');
         
         var commentDisplay = updateCommentTextArea.style.display;
         if(commentDisplay != '')
         {
           updateCommentTextArea.style.display = '';
           updateCommentTextArea.style.visibility = 'hidden';
         }
         var uploadFileAttachment = document.getElementById('UPLOAD_FILE_ATTACHMENT');
         
         uploadFileAttachment.style.display = ''
         uploadFileAttachment.style.visibility = ''
       }
       
       function hideAddAttachmentWindow()
       {
         var updateCommentTextArea = document.getElementById('UPDATE_COMMENT_TEXTAREA');
         var uploadFileAttachment = document.getElementById('UPLOAD_FILE_ATTACHMENT');
         uploadFileAttachment.style.display = 'none'
         uploadFileAttachment.style.visibility = 'hidden'
         var commentV = updateCommentTextArea.style.visibility;
         if(commentV == 'hidden')
         {
           updateCommentTextArea.style.display = 'none';
         }
       }
    </script>
    <!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
    <!-- Display Task Header (Important) Attributes -->
    <!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
    <!-- Task Details table (Read Only Attributes)-->
    
          <!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
          <!-- Display Task Footer Attributes -->
          <!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
          <!-- Task Details table (Read Only Attributes)-->
          <form name="FOOTERUPDATE"  style="display:inline;" method="POST" action="WFTaskUpdate">
            <input type="HIDDEN"  name="<%=Constants.WORKLIST_TASKID_PARAMETER_NAME%>" value="<%=taskId%>"/>
            <input type="HIDDEN"  name="<%=Constants.WORKLIST_CONTEXT_PARAMETER_NAME%>" value="<%=contextId%>"/>
            <input type="HIDDEN"  name="<%=Constants.WFTASKNOTM_KEY_NAME%>" value="<%=task.getSystemAttributes().getNumberOfTimesModified()%>"/>
            <input type="HIDDEN"  name="<%=Constants.WORKLIST_NEXT_PAGE_PARAMETER_NAME%>" value="<%=nextPage%>"/>
            <input type="HIDDEN"  name="<%=Constants.WORKLIST_LOGIN_PAGE_PARAMETER_NAME%>" value="<%=loginPage%>"/>
            <input type="HIDDEN"  name="<%=Constants.WORKLIST_ERROR_PAGE_PARAMETER_NAME%>" value="<%=errorPage%>"/>
          <table id="FooterTable" width="100%" align="center" class="HeaderPane">
            <tr>
            <td width="55%" align="left" valign="top" class="bold">
              <%=Resource.getDisplayString(ResourceKeyConstants.COMMENTS_KEY, locale)%>
              <%
                if(WorklistUtil.isTaskUpdatable(task, taskVersion))
                {
              %>
                  <input type="button" value=" + "
                                onClick="showUpdateCommentWindow()"/>
                  <input type="button" value=" - "
                                onClick="hideUpdateCommentWindow()"/>
              <%
                }
              %>
              
            </td>
            <td  width="45%"  align="left" valign="top" class="bold"> 
            <%=Resource.getDisplayString(ResourceKeyConstants.ATTACHMENTS_KEY, locale)%>
            <%
                if(WorklistUtil.isTaskUpdatable(task, taskVersion))
                {
             %>
                  <input type="button" value=" + "
                                onClick="showAddAttachmentWindow()"/>
                  <input type="button" value=" - "
                                onClick="hideAddAttachmentWindow()"/>
            <%
               }
            %>
           
            </td>
            </tr>                        
            <tr>
            <td align="left" valign="top">
              <%
              out.println("<textarea cols=\"70\" rows=\"5\" style=\"background-color:WhiteSmoke\" readonly=\"readonly\">"); 
              List userComments = task.getUserComment();
              for(int i = 0 ; i < userComments.size() ; i++)
              {
                CommentType type = (CommentType)userComments.get(i);
                String message = type.getComment();
                IdentityType idType = type.getUpdatedBy();
                if(idType != null)
                {
                  message = "[" + idType.getId() + "] " + message;
                }
                out.println(message);
              }
              out.println("</textarea>");
              %>
            </td>
            <td  align="left" valign="top"> 
              <table  align="left" id="pcwtable">
              <%
              
              List attachments = task.getAttachment();
              if (attachments != null)
              {            
                int numAttachments = attachments.size();
                
                for (int j=0; j < numAttachments; j++)
                 {
                    AttachmentType thisAttachment = 
                                (AttachmentType) attachments.get(j);
                    if ((j % 4) == 0)
                      out.println("<tr class=\"odd\">");
                    else if ((j % 2) == 0)
                      out.println("<tr class=\"even\">");
                    if (thisAttachment.getURI() != null)
                    {
                      out.println("<td width=\"200\">");
                      out.println("<input name=\"" + 
                                         Constants.WFTASK_ATTACHMENT_NAME_KEY + 
                                         "\" type=\"CHECKBOX\" value=\"" + 
                                         thisAttachment.getName() + "\"/>");
                      out.println("<a class=\"blue\" href=\"" + 
                               thisAttachment.getURI() + "\" target=\"_blank\">" + 
                               thisAttachment.getName() + "</a> " + 
                               Resource.getDisplayString
                               (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_SUFFIX_LINK, locale) +
                               "</td>");                              
                    }
                    else 
                    {                  
                      out.println("<td width=\"200\">");
                      String attachLink = getTaskUpdateURL(thisAttachment.getTaskId(),
                                                        contextId,
                                                        thisAttachment.getVersion() +"",
                                                        nextPage,
                                                        loginPage,
                                                        thisAttachment.getName(),
                                                        Constants.WFTASK_QUERY_ATTACHMENT_BUTTON_KEY_NAME);
                      
                      out.println("<input name=\"" + 
                                         Constants.WFTASK_ATTACHMENT_NAME_KEY + 
                                         "\" type=\"CHECKBOX\" value=\"" + 
                                         thisAttachment.getName() + "\"/>");
                      out.println("<a class=\"blue\" href=\""+ attachLink + 
                             "\" target=\"_blank\">" + thisAttachment.getName() + "</a>");  
                      out.println(" " + 
                           Resource.getDisplayString
                             (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_SUFFIX_FILE, locale) + 
                          "</td>");                              
                    }
                    if ((j % 2) == 1)
                      out.println("</tr>");
                 }
                 if (numAttachments == 0)
                 {
                    out.println("<tr class=\"even\"><td colspan=\"3\">" + 
                                Resource.getDisplayString
                                (ResourceKeyConstants.MESSAGE_TASK_DETAILS_NO_ATTACHMENTS,locale) 
                            + "</tr>");                       
                 }
                 else
                 {
                    %>
                      <tr><td>
                      <input type="SUBMIT" 
                           name="<%=Constants.WFTASK_DELETE_ATTACHMENT_BUTTON_KEY_NAME%>" value="Delete" OnClick="callUpdate()"/>
                       </td>
                       </tr>
                    <%
                 }
               }
              
            %>
            </table>
            </td>
            </tr>      
            <tr>
              <td id="UPDATE_COMMENT_TEXTAREA"  align="left" valign="top" style="display:none">
               <textarea name="<%=Constants.WFTASKCOMMENT_COMMENT_TEXT_KEY_NAME%>"
                  cols="70" rows="5" style="background-color:white;"></textarea>
                <br/>
                 
                  <input name="<%=Constants.WFTASKCOMMENT_UPDATE_BUTTON_KEY_NAME%>" 
                          value="<%=Resource.getDisplayString
                                (ResourceKeyConstants.UPDATE_KEY,locale)%>" 
                                type="SUBMIT" />
              </td>
              <td COLSPAN="2" id="UPLOAD_FILE_ATTACHMENT" align="left" valign="top" style="display:none">
                   <!-- Start of URL adding -->
                   <table width="100%" align="center" id="pcwtableurl">
                    <tr class="header">
                       <td colspan="2">
                           <b>
                           <%=Resource.getDisplayString
                                     (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_ADD_URL, 
                                     locale)
                            %>
                            </b>
                          </td>
                      </tr>
           
                      <tr  class="even">     
                        <td> 
                          <%=Resource.getDisplayString
                                     (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_URL_NAME, 
                                     locale)
                           %>
                        </td>
                        <td> 
                         <input type="TEXT" 
                             name="<%=Constants.WFTASK_ATTACHMENT_URLNAME_KEY_NAME%>" 
                              size="40"  maxlength="255"/>
                        </td>
                      </tr>
                      <tr  class="odd">     
                        <td> 
                           <%=Resource.getDisplayString
                                     (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_URL_URL,
                                     locale)
                           %>
                         </td>
                         <td> 
                           <input type="TEXT" 
                             name="<%=Constants.WFTASK_ATTACHMENT_URL_KEY_NAME%>" 
                               size="40"  maxlength="255"/>
                               (<%=Resource.getDisplayString
                                     (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_URL_EXAMPLE,
                                     locale)
                                  %> http://www.oracle.com)
                         </td>
                       </tr>     
                       <tr>     
                         <td> 
                           <input type="SUBMIT" 
                             value="<%=Resource.getDisplayString
                                     (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_ADDURL_BUTTON_NAME, 
                                     locale)%> " 
                                     name="<%=Constants.WFTASK_ADDURL_ATTACHMENT_BUTTON_KEY_NAME%>"/>
                         </td>     
                       </tr>     
                    </table>
                    <!-- end  of adding URL -->
                </form>
                    <!-- start adding attachment file -->
                    <form name="FOOTERUPDATE"  
                         style="display:inline;" enctype="multipart/form-data" method="POST" 
                         action="<%=getTaskUpdateURL(taskId,contextId,null,nextPage,loginPage,null,Constants.WFTASK_UPLOAD_ATTACHMENT_BUTTON_KEY_NAME)%>">
                         <!-- start adding attachment file -->
                   <table width="100%" align="center" id="addattachment">
                     <tr class="header">
                       <td>
                       <b><%=Resource.getDisplayString
                             (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_ADD_FILE, locale)
                            %>
                            </b>
                      </td>
                    </tr>
                    <tr>
                     <td> 
                        <%=Resource.getDisplayString
                            (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_FILE_NAME, 
                             locale)
                          %>
                       <input type="FILE" 
                         <% out.println("name=\"" + Constants.WFTASK_ATTACHMENT_NAME_KEY + "\""); %> size="30"  maxlength="255"/>
                        <br></br>
                        <input type="SUBMIT" value="<%=Resource.getDisplayString
                                     (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_UPLOAD_BUTTON_NAME, 
                                locale)%>"
                                name="<%=Constants.WFTASK_UPLOAD_ATTACHMENT_BUTTON_KEY_NAME%>"/>
                      </td>
                     </tr>
                   </table>
                  </form>
                  <!-- END of adding Attachment file -->
              </td>
             </tr>
          </table>
        <%!
           private String getTaskUpdateURL(String taskId,
                                           String contextId,
                                           String taskVersion,
                                           String nextPage,
                                           String loginPage,
                                           String attName,
                                           String actionType)  
           {
              try
              {
                StringBuffer buffer = new StringBuffer();
                buffer.append("WFTaskUpdate?")
                    .append(actionType).append("=").append(URLEncoder.encode(actionType,"UTF-8")).append("&")
                    .append(Constants.WORKLIST_TASKID_PARAMETER_NAME).append("=").append(URLEncoder.encode(taskId,"UTF-8")).append("&")
                    .append(Constants.WORKLIST_CONTEXT_PARAMETER_NAME).append("=").append(URLEncoder.encode(contextId,"UTF-8")).append("&")
                    .append(Constants.WORKLIST_NEXT_PAGE_PARAMETER_NAME).append("=").append(URLEncoder.encode(nextPage,"UTF-8")).append("&")
                    .append(Constants.WORKLIST_LOGIN_PAGE_PARAMETER_NAME).append("=").append(URLEncoder.encode(loginPage,"UTF-8"));
                if(taskVersion != null)
                {
                  buffer.append("&").append(Constants.WORKLIST_TASK_VERSION_PARAMETER_NAME).append("=").append(URLEncoder.encode(taskVersion,"UTF-8")).append("&");
                }
                if(attName != null)
                {
                  buffer.append("&").append(Constants.WFTASK_ATTACHMENT_NAME_KEY).append("=").append(URLEncoder.encode(attName,"UTF-8"));
                }
                return buffer.toString();
               }
               catch(UnsupportedEncodingException e)
               {
                 return null;
               }
           }
        %>
