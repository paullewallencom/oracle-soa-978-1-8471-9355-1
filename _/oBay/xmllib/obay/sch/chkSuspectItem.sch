<schema xmlns="http://www.ascc.net/xml/schematron">
    <!-- define the process namespace for finding the elements in the rule-->
    <ns uri="http://schemas.packtpub.com/obay/lstx" prefix="lst"/>
    <ns uri="http://schemas.packtpub.com/obay/cmn" prefix="cmn"/>
    <ns uri="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" prefix="xp20"/>
    <pattern name="Check for Suspect Item"> 
        <rule context="lst:listingDetail/lst:item">
            <assert test="contains(xp20:lower-case(lst:title), 'gun')">
                Item has suspect title.
            </assert>
            <assert test="contains(xp20:lower-case(lst:title), 'sex')">
                Item has suspect title.
            </assert>
            <assert test="contains(xp20:lower-case(lst:title), 'drug')">
                Item has suspect title.
            </assert>
        </rule>
    </pattern>
</schema>