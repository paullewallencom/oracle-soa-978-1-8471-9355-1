<schema xmlns="http://www.ascc.net/xml/schematron">
    <!-- define the process namespace for finding the elements in the rule-->
    <ns uri="http://schemas.packtpub.com/obay/usr" prefix="usr"/>
    <ns uri="http://schemas.packtpub.com/obay/cmn" prefix="cmn"/>
    <ns uri="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" prefix="xp20"/>
    <pattern name="Check User Details"> 
        <rule context="usr:user">
            <assert test="xp20:matches(usr:userId, '[0-9A-Za-z]{6,20}')">
                User Id must be alpha numeric and between 6 and 20 characters in length.
            </assert>
            <assert test="xp20:current-date() > xp20:add-dayTimeDuration-to-dateTime(cmn:dob,'P18YT')">
                You must be over 18 years of age to register with oBay.
            </assert>
        </rule>
    </pattern>
</schema>