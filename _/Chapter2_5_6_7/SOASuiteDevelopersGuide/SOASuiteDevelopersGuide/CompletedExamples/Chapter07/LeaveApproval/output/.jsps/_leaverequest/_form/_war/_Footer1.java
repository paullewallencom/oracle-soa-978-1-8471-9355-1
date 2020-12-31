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
import oracle.bpel.services.workflow.task.model.CommentType;
import oracle.bpel.services.workflow.task.model.AttachmentType;
import oracle.bpel.services.workflow.task.model.IdentityType;
import oracle.bpel.services.workflow.worklist.display.Resource;
import oracle.bpel.services.workflow.worklist.display.ResourceKeyConstants;
import oracle.bpel.services.workflow.worklist.servlet.Constants;
import oracle.bpel.services.workflow.worklist.api.util.WorklistUtil;
import oracle.bpel.services.workflow.worklist.api.payload.PayloadConstant;;


public class _Footer1 extends com.orionserver.http.OrionHttpJspPage {


  // ** Begin Declarations


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
    _Footer1 page = this;
    ServletConfig config = pageContext.getServletConfig();

    try {


      out.write(__oracle_jsp_text[0]);
      out.write(__oracle_jsp_text[1]);
      out.write(__oracle_jsp_text[2]);
      
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
            
          
      out.write(__oracle_jsp_text[3]);
      
               if(styleSheetURL != null && !styleSheetURL.trim().equals(""))
               {
                 out.println("@import url(" + styleSheetURL + ");");
               }
            
      out.write(__oracle_jsp_text[4]);
      out.print(Constants.WORKLIST_TASKID_PARAMETER_NAME);
      out.write(__oracle_jsp_text[5]);
      out.print(taskId);
      out.write(__oracle_jsp_text[6]);
      out.print(Constants.WORKLIST_CONTEXT_PARAMETER_NAME);
      out.write(__oracle_jsp_text[7]);
      out.print(contextId);
      out.write(__oracle_jsp_text[8]);
      out.print(Constants.WFTASKNOTM_KEY_NAME);
      out.write(__oracle_jsp_text[9]);
      out.print(task.getSystemAttributes().getNumberOfTimesModified());
      out.write(__oracle_jsp_text[10]);
      out.print(Constants.WORKLIST_NEXT_PAGE_PARAMETER_NAME);
      out.write(__oracle_jsp_text[11]);
      out.print(nextPage);
      out.write(__oracle_jsp_text[12]);
      out.print(Constants.WORKLIST_LOGIN_PAGE_PARAMETER_NAME);
      out.write(__oracle_jsp_text[13]);
      out.print(loginPage);
      out.write(__oracle_jsp_text[14]);
      out.print(Constants.WORKLIST_ERROR_PAGE_PARAMETER_NAME);
      out.write(__oracle_jsp_text[15]);
      out.print(errorPage);
      out.write(__oracle_jsp_text[16]);
      out.print(Resource.getDisplayString(ResourceKeyConstants.COMMENTS_KEY, locale));
      out.write(__oracle_jsp_text[17]);
      
                      if(WorklistUtil.isTaskUpdatable(task, taskVersion))
                      {
                    
      out.write(__oracle_jsp_text[18]);
      
                      }
                    
      out.write(__oracle_jsp_text[19]);
      out.print(Resource.getDisplayString(ResourceKeyConstants.ATTACHMENTS_KEY, locale));
      out.write(__oracle_jsp_text[20]);
      
                      if(WorklistUtil.isTaskUpdatable(task, taskVersion))
                      {
                   
      out.write(__oracle_jsp_text[21]);
      
                     }
                  
      out.write(__oracle_jsp_text[22]);
      
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
                    
      out.write(__oracle_jsp_text[23]);
      
                    
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
                          
      out.write(__oracle_jsp_text[24]);
      out.print(Constants.WFTASK_DELETE_ATTACHMENT_BUTTON_KEY_NAME);
      out.write(__oracle_jsp_text[25]);
      
                       }
                     }
                    
                  
      out.write(__oracle_jsp_text[26]);
      out.print(Constants.WFTASKCOMMENT_COMMENT_TEXT_KEY_NAME);
      out.write(__oracle_jsp_text[27]);
      out.print(Constants.WFTASKCOMMENT_UPDATE_BUTTON_KEY_NAME);
      out.write(__oracle_jsp_text[28]);
      out.print(Resource.getDisplayString
                                (ResourceKeyConstants.UPDATE_KEY,locale));
      out.write(__oracle_jsp_text[29]);
      out.print(Resource.getDisplayString
                                     (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_ADD_URL, 
                                     locale)
                            );
      out.write(__oracle_jsp_text[30]);
      out.print(Resource.getDisplayString
                                     (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_URL_NAME, 
                                     locale)
                           );
      out.write(__oracle_jsp_text[31]);
      out.print(Constants.WFTASK_ATTACHMENT_URLNAME_KEY_NAME);
      out.write(__oracle_jsp_text[32]);
      out.print(Resource.getDisplayString
                                     (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_URL_URL,
                                     locale)
                           );
      out.write(__oracle_jsp_text[33]);
      out.print(Constants.WFTASK_ATTACHMENT_URL_KEY_NAME);
      out.write(__oracle_jsp_text[34]);
      out.print(Resource.getDisplayString
                                     (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_URL_EXAMPLE,
                                     locale)
                                  );
      out.write(__oracle_jsp_text[35]);
      out.print(Resource.getDisplayString
                                     (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_ADDURL_BUTTON_NAME, 
                                     locale));
      out.write(__oracle_jsp_text[36]);
      out.print(Constants.WFTASK_ADDURL_ATTACHMENT_BUTTON_KEY_NAME);
      out.write(__oracle_jsp_text[37]);
      out.print(getTaskUpdateURL(taskId,contextId,null,nextPage,loginPage,null,Constants.WFTASK_UPLOAD_ATTACHMENT_BUTTON_KEY_NAME));
      out.write(__oracle_jsp_text[38]);
      out.print(Resource.getDisplayString
                             (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_ADD_FILE, locale)
                            );
      out.write(__oracle_jsp_text[39]);
      out.print(Resource.getDisplayString
                            (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_FILE_NAME, 
                             locale)
                          );
      out.write(__oracle_jsp_text[40]);
       out.println("name=\"" + Constants.WFTASK_ATTACHMENT_NAME_KEY + "\""); 
      out.write(__oracle_jsp_text[41]);
      out.print(Resource.getDisplayString
                                     (ResourceKeyConstants.LABEL_TASK_ATTACHMENT_UPLOAD_BUTTON_NAME, 
                                locale));
      out.write(__oracle_jsp_text[42]);
      out.print(Constants.WFTASK_UPLOAD_ATTACHMENT_BUTTON_KEY_NAME);
      out.write(__oracle_jsp_text[43]);
      out.write(__oracle_jsp_text[44]);

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
  private static final char __oracle_jsp_text[][]=new char[45][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "  <!-- BPM Worklist Express / TaskDetails.jsp -->  \n  ".toCharArray();
    __oracle_jsp_text[1] = 
    "\n  ".toCharArray();
    __oracle_jsp_text[2] = 
    "\n\n    \n    ".toCharArray();
    __oracle_jsp_text[3] = 
    " \n    <style type=\"text/css\">\n      @import url(headerfooter.css);\n      ".toCharArray();
    __oracle_jsp_text[4] = 
    "\n    </style>\n\n\n    <script>\n    \n       function trimString (str) \n       {\n          while (str.charAt(0) == ' ')\n            str = str.substring(1);\n          while (str.charAt(str.length - 1) == ' ')\n            str = str.substring(0, str.length - 1);\n          return str;\n       }\n\n\n       function showUpdateCommentWindow()\n       {\n         var updateCommentTextArea = document.getElementById('UPDATE_COMMENT_TEXTAREA');\n         //set the display to '' and visibility to ''\n         updateCommentTextArea.style.display  = '';\n         updateCommentTextArea.style.visibility  = '';\n         \n         var uploadFileAttachment = document.getElementById('UPLOAD_FILE_ATTACHMENT');\n         var attachmentDisplay = uploadFileAttachment.style.display;\n         //get the attachment display\n         // if it none then set display to '' and visiblity to hidden\n         if(attachmentDisplay != '')\n         {\n           uploadFileAttachment.style.visibility = 'hidden';\n         }\n         \n       }\n       function hideUpdateCommentWindow()\n       {\n         var updateCommentTextArea = document.getElementById('UPDATE_COMMENT_TEXTAREA');\n         var uploadFileAttachment = document.getElementById('UPLOAD_FILE_ATTACHMENT');\n         var hiddenFA = uploadFileAttachment.style.visibility;\n         if(trimString(hiddenFA) != 'hidden')\n         {\n           updateCommentTextArea.style.visibility = 'hidden';\n         }\n         else\n         {\n           uploadFileAttachment.style.display  = 'none';\n           uploadFileAttachment.style.visibility = 'hidden'\n           updateCommentTextArea.style.display  = 'none';\n           updateCommentTextArea.style.visibility = 'hidden'\n         }\n       }\n       \n       function showAddAttachmentWindow()\n       {\n         var updateCommentTextArea = document.getElementById('UPDATE_COMMENT_TEXTAREA');\n         \n         var commentDisplay = updateCommentTextArea.style.display;\n         if(commentDisplay != '')\n         {\n           updateCommentTextArea.style.display = '';\n           updateCommentTextArea.style.visibility = 'hidden';\n         }\n         var uploadFileAttachment = document.getElementById('UPLOAD_FILE_ATTACHMENT');\n         \n         uploadFileAttachment.style.display = ''\n         uploadFileAttachment.style.visibility = ''\n       }\n       \n       function hideAddAttachmentWindow()\n       {\n         var updateCommentTextArea = document.getElementById('UPDATE_COMMENT_TEXTAREA');\n         var uploadFileAttachment = document.getElementById('UPLOAD_FILE_ATTACHMENT');\n         uploadFileAttachment.style.display = 'none'\n         uploadFileAttachment.style.visibility = 'hidden'\n         var commentV = updateCommentTextArea.style.visibility;\n         if(commentV == 'hidden')\n         {\n           updateCommentTextArea.style.display = 'none';\n         }\n       }\n    </script>\n    <!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->\n    <!-- Display Task Header (Important) Attributes -->\n    <!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->\n    <!-- Task Details table (Read Only Attributes)-->\n    \n          <!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->\n          <!-- Display Task Footer Attributes -->\n          <!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->\n          <!-- Task Details table (Read Only Attributes)-->\n          <form name=\"FOOTERUPDATE\"  style=\"display:inline;\" method=\"POST\" action=\"WFTaskUpdate\">\n            <input type=\"HIDDEN\"  name=\"".toCharArray();
    __oracle_jsp_text[5] = 
    "\" value=\"".toCharArray();
    __oracle_jsp_text[6] = 
    "\"/>\n            <input type=\"HIDDEN\"  name=\"".toCharArray();
    __oracle_jsp_text[7] = 
    "\" value=\"".toCharArray();
    __oracle_jsp_text[8] = 
    "\"/>\n            <input type=\"HIDDEN\"  name=\"".toCharArray();
    __oracle_jsp_text[9] = 
    "\" value=\"".toCharArray();
    __oracle_jsp_text[10] = 
    "\"/>\n            <input type=\"HIDDEN\"  name=\"".toCharArray();
    __oracle_jsp_text[11] = 
    "\" value=\"".toCharArray();
    __oracle_jsp_text[12] = 
    "\"/>\n            <input type=\"HIDDEN\"  name=\"".toCharArray();
    __oracle_jsp_text[13] = 
    "\" value=\"".toCharArray();
    __oracle_jsp_text[14] = 
    "\"/>\n            <input type=\"HIDDEN\"  name=\"".toCharArray();
    __oracle_jsp_text[15] = 
    "\" value=\"".toCharArray();
    __oracle_jsp_text[16] = 
    "\"/>\n          <table id=\"FooterTable\" width=\"100%\" align=\"center\" class=\"HeaderPane\">\n            <tr>\n            <td width=\"55%\" align=\"left\" valign=\"top\" class=\"bold\">\n              ".toCharArray();
    __oracle_jsp_text[17] = 
    "\n              ".toCharArray();
    __oracle_jsp_text[18] = 
    "\n                  <input type=\"button\" value=\" + \"\n                                onClick=\"showUpdateCommentWindow()\"/>\n                  <input type=\"button\" value=\" - \"\n                                onClick=\"hideUpdateCommentWindow()\"/>\n              ".toCharArray();
    __oracle_jsp_text[19] = 
    "\n              \n            </td>\n            <td  width=\"45%\"  align=\"left\" valign=\"top\" class=\"bold\"> \n            ".toCharArray();
    __oracle_jsp_text[20] = 
    "\n            ".toCharArray();
    __oracle_jsp_text[21] = 
    "\n                  <input type=\"button\" value=\" + \"\n                                onClick=\"showAddAttachmentWindow()\"/>\n                  <input type=\"button\" value=\" - \"\n                                onClick=\"hideAddAttachmentWindow()\"/>\n            ".toCharArray();
    __oracle_jsp_text[22] = 
    "\n           \n            </td>\n            </tr>                        \n            <tr>\n            <td align=\"left\" valign=\"top\">\n              ".toCharArray();
    __oracle_jsp_text[23] = 
    "\n            </td>\n            <td  align=\"left\" valign=\"top\"> \n              <table  align=\"left\" id=\"pcwtable\">\n              ".toCharArray();
    __oracle_jsp_text[24] = 
    "\n                      <tr><td>\n                      <input type=\"SUBMIT\" \n                           name=\"".toCharArray();
    __oracle_jsp_text[25] = 
    "\" value=\"Delete\" OnClick=\"callUpdate()\"/>\n                       </td>\n                       </tr>\n                    ".toCharArray();
    __oracle_jsp_text[26] = 
    "\n            </table>\n            </td>\n            </tr>      \n            <tr>\n              <td id=\"UPDATE_COMMENT_TEXTAREA\"  align=\"left\" valign=\"top\" style=\"display:none\">\n               <textarea name=\"".toCharArray();
    __oracle_jsp_text[27] = 
    "\"\n                  cols=\"70\" rows=\"5\" style=\"background-color:white;\"></textarea>\n                <br/>\n                 \n                  <input name=\"".toCharArray();
    __oracle_jsp_text[28] = 
    "\" \n                          value=\"".toCharArray();
    __oracle_jsp_text[29] = 
    "\" \n                                type=\"SUBMIT\" />\n              </td>\n              <td COLSPAN=\"2\" id=\"UPLOAD_FILE_ATTACHMENT\" align=\"left\" valign=\"top\" style=\"display:none\">\n                   <!-- Start of URL adding -->\n                   <table width=\"100%\" align=\"center\" id=\"pcwtableurl\">\n                    <tr class=\"header\">\n                       <td colspan=\"2\">\n                           <b>\n                           ".toCharArray();
    __oracle_jsp_text[30] = 
    "\n                            </b>\n                          </td>\n                      </tr>\n           \n                      <tr  class=\"even\">     \n                        <td> \n                          ".toCharArray();
    __oracle_jsp_text[31] = 
    "\n                        </td>\n                        <td> \n                         <input type=\"TEXT\" \n                             name=\"".toCharArray();
    __oracle_jsp_text[32] = 
    "\" \n                              size=\"40\"  maxlength=\"255\"/>\n                        </td>\n                      </tr>\n                      <tr  class=\"odd\">     \n                        <td> \n                           ".toCharArray();
    __oracle_jsp_text[33] = 
    "\n                         </td>\n                         <td> \n                           <input type=\"TEXT\" \n                             name=\"".toCharArray();
    __oracle_jsp_text[34] = 
    "\" \n                               size=\"40\"  maxlength=\"255\"/>\n                               (".toCharArray();
    __oracle_jsp_text[35] = 
    " http://www.oracle.com)\n                         </td>\n                       </tr>     \n                       <tr>     \n                         <td> \n                           <input type=\"SUBMIT\" \n                             value=\"".toCharArray();
    __oracle_jsp_text[36] = 
    " \" \n                                     name=\"".toCharArray();
    __oracle_jsp_text[37] = 
    "\"/>\n                         </td>     \n                       </tr>     \n                    </table>\n                    <!-- end  of adding URL -->\n                </form>\n                    <!-- start adding attachment file -->\n                    <form name=\"FOOTERUPDATE\"  \n                         style=\"display:inline;\" enctype=\"multipart/form-data\" method=\"POST\" \n                         action=\"".toCharArray();
    __oracle_jsp_text[38] = 
    "\">\n                         <!-- start adding attachment file -->\n                   <table width=\"100%\" align=\"center\" id=\"addattachment\">\n                     <tr class=\"header\">\n                       <td>\n                       <b>".toCharArray();
    __oracle_jsp_text[39] = 
    "\n                            </b>\n                      </td>\n                    </tr>\n                    <tr>\n                     <td> \n                        ".toCharArray();
    __oracle_jsp_text[40] = 
    "\n                       <input type=\"FILE\" \n                         ".toCharArray();
    __oracle_jsp_text[41] = 
    " size=\"30\"  maxlength=\"255\"/>\n                        <br></br>\n                        <input type=\"SUBMIT\" value=\"".toCharArray();
    __oracle_jsp_text[42] = 
    "\"\n                                name=\"".toCharArray();
    __oracle_jsp_text[43] = 
    "\"/>\n                      </td>\n                     </tr>\n                   </table>\n                  </form>\n                  <!-- END of adding Attachment file -->\n              </td>\n             </tr>\n          </table>\n        ".toCharArray();
    __oracle_jsp_text[44] = 
    "\n".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
