<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="http://localhost:80/orabpel/obay/JobServices/JobServices?wsdl"/>
      <rootElement name="executeJob" namespace="http://xmlns.packtpub.com/obay/bs/Job"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="XSD">
      <schema location="http://localhost/orabpel/xmllib/ws-addressing.xsd"/>
      <rootElement name="EndpointReference" namespace="http://schemas.xmlsoap.org/ws/2003/03/addressing"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.4.0(build 080718.0645) AT [THU MAR 05 17:15:25 EST 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:plnk="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:sch="http://schemas.packtpub.com/obay/sch"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:wsa="http://schemas.xmlsoap.org/ws/2003/03/addressing"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:tns="http://xmlns.packtpub.com/obay/bs/Job"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                exclude-result-prefixes="xsl plnk sch xs soap tns wsa bpws ehdr hwf xp20 xref ora ids orcl">
  <xsl:template match="/">
    <wsa:EndpointReference>
      <wsa:Address>
        <xsl:value-of select="/tns:executeJob/sch:job/sch:endpoint"/>
      </wsa:Address>
    </wsa:EndpointReference>
  </xsl:template>
</xsl:stylesheet>
