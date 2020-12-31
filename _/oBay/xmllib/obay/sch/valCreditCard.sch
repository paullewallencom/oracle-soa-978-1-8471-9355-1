<schema xmlns="http://www.ascc.net/xml/schematron">
    <!-- define the process namespace for finding the elements in the rule-->
    <ns uri="http://xmlns.packtpub.com/updateCreditCard" prefix="upd"/>
    <ns uri="http://schemas.packtpub.com/obay/cmn" prefix="cmn"/>
    <ns uri="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" prefix="xp20"/>
    <pattern name="Check Credit Card"> 
        <rule context="cmn:creditCard">
            <assert test="cmn:cardType='MasterCard' or cmn:cardType='Visa' or cmn:cardType='Amex'">
                    Credit Card must be MasterCard, Visa or American Express.
            </assert>
            <assert test="cmn:expiryYear > xp20:year-from-dateTime(xp20:current-dateTime()) or 
                          (cmn:expiryYear= xp20:year-from-dateTime(xp20:current-dateTime()) and  
                          cmn:expiryMonth>=xp20:month-from-dateTime(xp20:current-dateTime()) )">
                Credit Card has expired.
            </assert>
        </rule>
        <rule context="cmn:creditCard[cmn:cardType='MasterCard']">
            <assert test="xp20:matches(cmn:cardNumber, '[0-9]{16}')">
                Mastercard number must be 16 digits.
            </assert>
            <assert test="string-length(cmn:securityNo) = '3'">
                Security code for Mastercard must be 3 digits.
            </assert>
        </rule>
        <rule context="cmn:creditCard[cmn:cardType='Visa']">
            <assert test="xp20:matches(cmn:cardNumber, '[0-9]{16}')">
                Visa number must be 16 digits.
            </assert>
            <assert test="string-length(cmn:securityNo) = '3'">
                Security code for Visa must be 3 digits.
            </assert>
        </rule>
        <rule context="cmn:creditCard[cmn:cardType='Amex']">
            <assert test="xp20:matches(cmn:cardNumber, '[0-9]{15}')">
                American Express number must be 15 digits.
            </assert>
            <assert test="string-length(cmn:securityNo) = '4'">
                Security code for American Express must be 4 digits.
            </assert>
        </rule>
    </pattern>
</schema>