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
import oracle.bpel.services.workflow.task.model.IdentityType;
import oracle.bpel.services.workflow.worklist.display.Resource;
import oracle.bpel.services.workflow.worklist.display.ResourceKeyConstants;
import oracle.bpel.services.workflow.worklist.servlet.Constants;


public class _Header1 extends com.orionserver.http.OrionHttpJspPage {


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
    _Header1 page = this;
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
      out.print(Resource.getDisplayString(ResourceKeyConstants.TASKNUMBER_KEY,locale));
      out.write(__oracle_jsp_text[17]);
      out.print(task.getSystemAttributes().getTaskNumber());
      out.write(__oracle_jsp_text[18]);
      out.print(Resource.getDisplayString(ResourceKeyConstants.TITLE_KEY,locale));
      out.write(__oracle_jsp_text[19]);
      out.print( task.getTitle() );
      out.write(__oracle_jsp_text[20]);
      out.print(Resource.getDisplayString(ResourceKeyConstants.IDENTIFICATION_KEY_KEY,locale));
      out.write(__oracle_jsp_text[21]);
       
                        String identKey = task.getIdentificationKey();
                        if(identKey == null) identKey = "";
                        out.println(identKey);
                     
      out.write(__oracle_jsp_text[22]);
      out.print(Resource.getDisplayString(ResourceKeyConstants.STATE_KEY,locale));
      out.write(__oracle_jsp_text[23]);
      out.print(Resource.getDisplayString("LABEL_TASK_STATE_"+task.getSystemAttributes().getState(),locale));
      out.write(__oracle_jsp_text[24]);
      out.print(Resource.getDisplayString(ResourceKeyConstants.CREATEDDATE_KEY,locale));
      out.write(__oracle_jsp_text[25]);
      
                      Calendar createDate = task.getSystemAttributes().getCreatedDate();
                      if(createDate != null)
                      {
                        out.println(dfshort.format(createDate.getTime()));
                      }
                    
      out.write(__oracle_jsp_text[26]);
      out.print(Resource.getDisplayString(ResourceKeyConstants.CREATOR_KEY,locale));
      out.write(__oracle_jsp_text[27]);
      
                        String creator = task.getCreator();
                        if(creator == null) creator = "";
                        out.println(creator);
                     
      out.write(__oracle_jsp_text[28]);
      out.print(Resource.getDisplayString(ResourceKeyConstants.OUTCOME_KEY,locale));
      out.write(__oracle_jsp_text[29]);
       
                        String outCome = task.getSystemAttributes().getOutcome();
                        if(outCome == null) outCome = "";
                        out.println(outCome);
                     
      out.write(__oracle_jsp_text[30]);
      out.print(Resource.getDisplayString(ResourceKeyConstants.UPDATEDDATE_KEY,locale));
      out.write(__oracle_jsp_text[31]);
      
                      Calendar updateDate = task.getSystemAttributes().getUpdatedDate();
                      if(updateDate != null)
                      {
                        out.println(dflong.format(updateDate.getTime()));
                      }
                    
      out.write(__oracle_jsp_text[32]);
      out.print(Resource.getDisplayString(ResourceKeyConstants.ACQUIREDBY_KEY,locale));
      out.write(__oracle_jsp_text[33]);
      
                          String aqBy = task.getSystemAttributes().getAcquiredBy();
                          if(aqBy == null) aqBy = "";
                          out.println(aqBy);
                       
      out.write(__oracle_jsp_text[34]);
      out.print(Resource.getDisplayString(ResourceKeyConstants.PRIORITY_KEY,locale));
      out.write(__oracle_jsp_text[35]);
      
                        int priority = task.getPriority();
                        
                        //TO DO add more checks status/permission
                        if(taskVersion == 0)
                        {
                          
      out.write(__oracle_jsp_text[36]);
      out.print(Constants.WFTASKPRIORITY_KEY_NAME);
      out.write(__oracle_jsp_text[37]);
      out.print(priority);
      out.write(__oracle_jsp_text[38]);
      out.print(priority);
      out.write(__oracle_jsp_text[39]);
      
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
                          
      out.write(__oracle_jsp_text[40]);
      
                         }
                         else
                         {
                           out.println("" + priority);
                         }
                       
      out.write(__oracle_jsp_text[41]);
      out.print(Resource.getDisplayString(ResourceKeyConstants.EXPIRATIONDATE_KEY,locale));
      out.write(__oracle_jsp_text[42]);
      
                      Calendar expDate = task.getSystemAttributes().getExpirationDate();
                      if(expDate != null)
                      {
                        out.print(dflong.format(expDate.getTime()));
                      }
                    
      out.write(__oracle_jsp_text[43]);
      out.print(Resource.getDisplayString(ResourceKeyConstants.ASSIGNEES_KEY,locale));
      out.write(__oracle_jsp_text[44]);
      
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
                     
      out.write(__oracle_jsp_text[45]);

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
  private static final char __oracle_jsp_text[][]=new char[46][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "  <!-- BPM Worklist Express / TaskDetails.jsp -->  \n  ".toCharArray();
    __oracle_jsp_text[1] = 
    "\n  ".toCharArray();
    __oracle_jsp_text[2] = 
    "\n\n    \n    ".toCharArray();
    __oracle_jsp_text[3] = 
    "\n\n    <style type=\"text/css\">\n      @import url(headerfooter.css);\n      ".toCharArray();
    __oracle_jsp_text[4] = 
    "\n    </style>\n \n    <!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->\n    <!-- Display Task Header (Important) Attributes -->\n    <!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->\n    <!-- Task Details table (Read Only Attributes)-->\n     <form name=\"PRIORITYUPDATE\" style=\"display:inline;\" method=\"POST\" action=\"WFTaskUpdate\">\n     \n     <input type=\"HIDDEN\"  name=\"".toCharArray();
    __oracle_jsp_text[5] = 
    "\" value=\"".toCharArray();
    __oracle_jsp_text[6] = 
    "\"/>\n     <input type=\"HIDDEN\"  name=\"".toCharArray();
    __oracle_jsp_text[7] = 
    "\" value=\"".toCharArray();
    __oracle_jsp_text[8] = 
    "\"/>\n     <input type=\"HIDDEN\"  name=\"".toCharArray();
    __oracle_jsp_text[9] = 
    "\" value=\"".toCharArray();
    __oracle_jsp_text[10] = 
    "\"/>\n\n      <input type=\"HIDDEN\"  name=\"".toCharArray();
    __oracle_jsp_text[11] = 
    "\" value=\"".toCharArray();
    __oracle_jsp_text[12] = 
    "\"/>\n      <input type=\"HIDDEN\"  name=\"".toCharArray();
    __oracle_jsp_text[13] = 
    "\" value=\"".toCharArray();
    __oracle_jsp_text[14] = 
    "\"/>\n      <input type=\"HIDDEN\"  name=\"".toCharArray();
    __oracle_jsp_text[15] = 
    "\" value=\"".toCharArray();
    __oracle_jsp_text[16] = 
    "\"/>\n    \n     <table width=\"100%\" align=\"center\" class=\"HeaderPane\"  >\n        <tr style=\"height:20px\">\n           <!-- First Column Label & Data -->\n           <td width=\"12%\" class=\"bold\" align=\"right\">\n             ".toCharArray();
    __oracle_jsp_text[17] = 
    ":\n           </td>\n           <td width=\"13%\" align=\"left\">\n             ".toCharArray();
    __oracle_jsp_text[18] = 
    "\n           </td>          \n \n           <!-- Second Column Label & Data -->\n           <td width=\"15%\" class=\"bold\" align=\"right\">\n             ".toCharArray();
    __oracle_jsp_text[19] = 
    ":\n           </td>\n           <td width=\"25%\" align=\"left\">\n             ".toCharArray();
    __oracle_jsp_text[20] = 
    "\n           </td>          \n\n           <!-- Third Column Label & Data -->\n            <td width=\"15%\" align=\"right\" class=\"bold\">\n             ".toCharArray();
    __oracle_jsp_text[21] = 
    "\n            </td>\n            <td width=\"20%\" align=\"left\" >\n              ".toCharArray();
    __oracle_jsp_text[22] = 
    "\n            </td>\n\n        </tr>\n\n        <tr style=\"height:20px\">\n             <td align=\"right\" class=\"bold\">\n                 ".toCharArray();
    __oracle_jsp_text[23] = 
    ":\n             </td>\n             <td align=\"left\">\n                ".toCharArray();
    __oracle_jsp_text[24] = 
    "\n             </td>\n             \n             <td align=\"right\" class=\"bold\">\n                ".toCharArray();
    __oracle_jsp_text[25] = 
    ":\n             </td>\n             <td align=\"left\">\n              ".toCharArray();
    __oracle_jsp_text[26] = 
    "\n             </td>\n             <td align=\"right\" class=\"bold\" style=\"word-space:nowrap;\">\n                ".toCharArray();
    __oracle_jsp_text[27] = 
    ":\n             </td>\n             <td align=\"left\" >\n               ".toCharArray();
    __oracle_jsp_text[28] = 
    "\n             </td>\n        </tr>     \n        <tr style=\"height:20px\">\n             <td align=\"right\" class=\"bold\">\n                 ".toCharArray();
    __oracle_jsp_text[29] = 
    ":\n             </td>\n             <td  align=\"left\">\n               ".toCharArray();
    __oracle_jsp_text[30] = 
    "\n             </td>\n             \n             <td  align=\"right\" class=\"bold\">\n                ".toCharArray();
    __oracle_jsp_text[31] = 
    ":\n             </td>\n             <td  align=\"left\">\n              ".toCharArray();
    __oracle_jsp_text[32] = 
    "\n             </td>\n             <td  align=\"right\" class=\"bold\" style=\"word-space:nowrap;\">\n                ".toCharArray();
    __oracle_jsp_text[33] = 
    ":\n             </td>\n             <td align=\"left\" >\n                 ".toCharArray();
    __oracle_jsp_text[34] = 
    "\n             </td>\n        </tr>\n      \n        <tr style=\"height:20px\">\n             <td align=\"right\" class=\"bold\">\n                ".toCharArray();
    __oracle_jsp_text[35] = 
    ":\n             </td>\n             <td align=\"left\" >\n              \n               ".toCharArray();
    __oracle_jsp_text[36] = 
    "\n                      <select name=\"".toCharArray();
    __oracle_jsp_text[37] = 
    "\">\n                      <option value=\"".toCharArray();
    __oracle_jsp_text[38] = 
    "\">".toCharArray();
    __oracle_jsp_text[39] = 
    "</option>\n                    ".toCharArray();
    __oracle_jsp_text[40] = 
    "\n                    </select>\n                 ".toCharArray();
    __oracle_jsp_text[41] = 
    "\n             </td>\n             <td align=\"right\" class=\"bold\" >\n                ".toCharArray();
    __oracle_jsp_text[42] = 
    ":\n             </td>\n             <td  align=\"left\" >\n               ".toCharArray();
    __oracle_jsp_text[43] = 
    "\n             </td>\n             <td  align=\"right\" class=\"bold\" style=\"white-space:nowrap;\">\n                ".toCharArray();
    __oracle_jsp_text[44] = 
    ":\n             </td>\n             <td  align=\"left\" >\n               ".toCharArray();
    __oracle_jsp_text[45] = 
    "\n             </td>\n        </tr> \n       </table>\n      </form> \n".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
