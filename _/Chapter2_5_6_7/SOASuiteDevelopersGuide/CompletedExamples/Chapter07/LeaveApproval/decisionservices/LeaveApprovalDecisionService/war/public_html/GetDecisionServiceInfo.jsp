<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.io.*" %>
<%@page import="java.net.*" %>
<%@page import="java.util.*" %>
<%@page import="javax.xml.bind.*" %>
<%@page import="org.w3c.dom.*" %>
<%@page import="com.collaxa.cube.fe.util.FormatUtils" %>
<%@page import="oracle.bpel.services.common.util.XMLUtil" %>
<%@page import="oracle.bpel.services.rules.cache.IHierarchicalCache"%>
<%@page import="oracle.bpel.services.rules.cache.PathFunctions"%>
<%@page import="oracle.bpel.services.rules.cache.CacheFactory"%>
<%@page import="oracle.bpel.services.rules.common.IDecisionServiceConstants"%>
<%@page import="oracle.bpel.services.rules.impl.DecisionServiceCache"%>
<%@page import="oracle.bpel.services.rules.rpi.IRuleEngine" %>
<%@page import="oracle.bpel.services.rules.rpi.RuleEngineFactory" %>
<%@page import="oracle.bpel.services.rules.rpi.model.*" %>
<%@page import="oracle.bpel.services.rules.util.DecisionServiceBuilder" %>
<%@page import="oracle.bpel.services.rules.util.StringUtil" %>

<%


Locale locale = Locale.getDefault(); 



try
{
  RuleEngineFactory reFactory = RuleEngineFactory.getInstance();
  IHierarchicalCache cache = CacheFactory.getInstance().createCache();
  Unmarshaller unmarshaller = reFactory.createUnmarshaller();
  Marshaller marshaller = reFactory.createMarshaller();
  TDecisionService decisionService = null;
  TRuleEngineProvider ruleEngineProvider = null;
  TRuleEngineRepository ruleRepository;
  List patternList;
  int patternListSize;

  String pathArray[] = new String[] 
  {
    "${domain_id}",
    "${process_id}",
    "${process_revision}",
    "${service_name}"
  };

  String decisionServiceName = "${service_name}";
  String key = request.getParameter("key");

  String path = PathFunctions.createPath(pathArray);

  String svcPath = path + DecisionServiceCache.DECISIONSERVICE_PATH;
  String repPath = path + DecisionServiceCache.RULEENGINE_PATH;
  String repositoryLocation = StringUtil.getDisplayString("MESSAGE_UNKNOW_REPOSITORY", locale);

  boolean isErrored = request.getParameter("errored") != null;


  if ((path == null) || (decisionServiceName == null))
  {
    out.println(StringUtil.getDisplayString("MESSAGE_PARAMETER_MISSING", locale));
    return;
  }
  
  if (isErrored)
  {
    out.println(StringUtil.getDisplayString("MESSAGE_RULES_ERROR", locale));
    return;
  }

  if (cache.exists(svcPath))
  {
    IRuleEngine ruleEngine;
    decisionService = (TDecisionService) cache.get(svcPath, decisionServiceName);
    ruleEngine      = (IRuleEngine) cache.get(repPath, decisionService.getRuleEngineProviderReference());
    ruleEngineProvider = ruleEngine.getProvider();
  }
  else
  {
    String blankFreeFileName;
    DecisionServiceBuilder dsBuilder;
    DecisionServices decisionServices;
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    URL dsConfigUrl = classLoader.getResource(IDecisionServiceConstants.DECISIONSERVICE_FILE);

    blankFreeFileName = dsConfigUrl.toExternalForm();
    blankFreeFileName = blankFreeFileName.replaceAll(" ", "%20");

    File dsConfigFile = new File(new URI(blankFreeFileName));
    
    decisionServices = (DecisionServices) unmarshaller.unmarshal(dsConfigFile);

    dsBuilder = new DecisionServiceBuilder(decisionServices);
    decisionService = dsBuilder.getDecisionService(decisionServiceName);
    ruleEngineProvider = dsBuilder.getRuleEngineProvider(decisionService.getRuleEngineProviderReference());
  }

  ruleRepository = ruleEngineProvider.getRepository();
  if (ruleRepository.getType() == TRuleEngineRepositoryType.FILE)
  {
    repositoryLocation = reFactory.getAbsoluteLocation(ruleRepository.getFile());
  }
  else if (ruleRepository.getType() == TRuleEngineRepositoryType.WEB_DAV)
  { 
    repositoryLocation = ruleRepository.getWebdav().getUrl();
  }
  else if (ruleRepository.getType() == TRuleEngineRepositoryType.SERVICE)
  {
    repositoryLocation = ruleRepository.getService().getUrl();
  }

%>
  <table style="color:#444444;font:11px verdana">
    <tr>
      <td colspan="4"> <b><%= decisionService.getName() %></b> </td>
    </tr>
    <tr>
      <td colspan="4"><b><%= StringUtil.getDisplayString("RULE_ENGINE", locale)%></b></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td align=left><%= StringUtil.getDisplayString("RULE_ENGINE_VENDOR", locale)%></td>
      <td align=left><%= ruleEngineProvider.getProvider().toString()%></td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td align=left><%= StringUtil.getDisplayString("RULE_REPOSITORY", locale)%></td>
      <td align=left><%= repositoryLocation%></td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td align=left><%= StringUtil.getDisplayString("RULE_CATALOG", locale)%></td>
      <td align=left><%=decisionService.getCatalog()%></td>
      <td>&nbsp;</td>
    </tr>
<%
    if (decisionService.getCatalogVersion() != null)
    {
%>
    <tr>
      <td>&nbsp;</td>
      <td align=left><%= StringUtil.getDisplayString("RULE_CATALOG_VERSION", locale)%></td>
      <td align=left><%=decisionService.getCatalogVersion()%></td>
      <td>&nbsp;</td>
    </tr>
<%
    }
%>
<%
    if (decisionService.getRuleset() != null)
    {
%>
    <tr>
      <td>&nbsp;</td>
      <td align=left><%= StringUtil.getDisplayString("RULE_SET", locale)%></td>
      <td align=left><%=decisionService.getRuleset()%></td>
      <td>&nbsp;</td>
    </tr>
<%
    }
%>

<%
    if (ruleEngineProvider.getProvider() == TRuleEngineProviderName.ORACLE)
    {
      String ruleAuthorURL = "http://${hostname}:${port}/ruleauthor/ConnectRepos.uix?event=dlConnect&ReposType=";
      if (ruleRepository.getType() == TRuleEngineRepositoryType.FILE)
      {
        ruleAuthorURL = ruleAuthorURL + "oracle.rules.sdk.store.jar&oracle.rules.sdk.store.jar.path=" + 
                        repositoryLocation;
      }
      else if (ruleRepository.getType() == TRuleEngineRepositoryType.WEB_DAV)
      {
        ruleAuthorURL = ruleAuthorURL + "oracle.rules.sdk.store.webdav&oracle.rules.sdk.store.webdav.url=" + 
                        repositoryLocation;
      }

      ruleAuthorURL = ruleAuthorURL + "&Dict=" +
                      decisionService.getCatalog();
      if (decisionService.getCatalogVersion() != null)
      {
        ruleAuthorURL = ruleAuthorURL + "&Version=" +
                        decisionService.getCatalogVersion();
      }
      if (decisionService.getRuleset() != null)
      {
        ruleAuthorURL = ruleAuthorURL + "&RuleSet=" + 
                        decisionService.getRuleset();
      }

%>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td align=left>
        <a href="<%=ruleAuthorURL%>"><%= StringUtil.getDisplayString("LABEL_OPEN_RULEAUTHOR", locale)%></a>
      </td>
      <td>&nbsp;</td>
    </tr>

<%
    }
%>
    <tr>
      <td colspan="4"><b><%= StringUtil.getDisplayString("RULE_INTERACTION", locale)%></b></td>
    </tr>
<%
  patternList = decisionService.getPattern();
  patternListSize = patternList.size();
  for (int i = 0; i < patternListSize; i++)
  {
    TPattern pattern = (TPattern) patternList.get(i);
    TArgumentList argumentList = pattern.getArguments();
%>
  <tr>
    <td>&nbsp;</td>
    <td align=left><%=pattern.getName()%></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>

<%
    if ((pattern.getName() == TDecisionServicePatternName.ASSERT) ||
        (pattern.getName() == TDecisionServicePatternName.ASSERT_EXECUTE) ||
        (pattern.getName() == TDecisionServicePatternName.ASSERT_EXECUTE_WATCH_STATELESS) ||
        (pattern.getName() == TDecisionServicePatternName.ASSERT_EXECUTE_WATCH_STATEFUL))
    {
      List assertList = argumentList.getAssert();

%>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td align=left><%= StringUtil.getDisplayString("LABEL_INPUT", locale)%></td>
    <td>&nbsp;</td>
  </tr>
<%

      for (int j = 0; j < assertList.size(); j++)
      {

%>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td align=left>&nbsp;&nbsp;<%= ((TAssert)assertList.get(j)).getValue()%></td>
    <td>&nbsp;</td>
  </tr>
<%
      }

    }
%>

<%
    if ((pattern.getName() == TDecisionServicePatternName.WATCH) ||
        (pattern.getName() == TDecisionServicePatternName.ASSERT_EXECUTE_WATCH_STATELESS) ||
        (pattern.getName() == TDecisionServicePatternName.ASSERT_EXECUTE_WATCH_STATEFUL))
    {
      List watchList = argumentList.getWatch();
%>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td align=left><%= StringUtil.getDisplayString("LABEL_OUTPUT", locale)%></td>
    <td>&nbsp;</td>
  </tr>
<%

      for (int j = 0; j < watchList.size(); j++)
      {

%>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td align=left>&nbsp;&nbsp;<%= (String)watchList.get(j)%></td>
    <td>&nbsp;</td>
  </tr>
<%
      }
    }
%>

<%
    if ((pattern.getName() == TDecisionServicePatternName.CALL_FUNCTION_STATELESS) ||
        (pattern.getName() == TDecisionServicePatternName.CALL_FUNCTION_STATEFUL))
    {
      String funCall = argumentList.getCall();

%>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td align=left><%= funCall%></td>
    <td>&nbsp;</td>
  </tr>

<%
    }
%>

<%    
  }
%>
  </table>
  <p/>

<%
}
catch(Throwable t)
{
  try 
  {
    out.clear();
  } catch(Throwable t2) {}
%>
  <%=StringUtil.getDisplayString("MESSAGE_RULES_AUDIT_TRAIL_ERROR", locale)%>
  <p/>  
<%
  t.printStackTrace(new java.io.PrintWriter(out, true));
}

%>
