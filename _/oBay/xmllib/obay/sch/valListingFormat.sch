<schema xmlns="http://www.ascc.net/xml/schematron">
    <!-- define the process namespace for finding the elements in the rule-->
    <ns uri="http://schemas.packtpub.com/obay/lst" prefix="lst"/>
    <ns uri="http://schemas.packtpub.com/obay/auc" prefix="auc"/>
    <ns uri="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" prefix="xp20"/>
    <pattern name="Check Lisitng Format Details">
        <rule context="lst:listingDetail">
            <assert test="lst:duration='P1D' or lst:duration='P3D' or lst:duration='P7D' or lst:duration='P10D'">
                Duration should be 1, 3, 7 or 10 days.
            </assert>
        </rule>
        <rule context="lst:listingDetail/lst:item">
            <assert test="lst:condition='NEW' or lst:condition='USED'">
                Item condition must be NEW or USED.
            </assert>
        </rule>  
    </pattern>
    <pattern name="Check Lisitng Format Details"> 
        <rule context="lst:listingDetail[string-length(lst:startTime) &gt; 0]">
            <assert test="xp20:current-dateTime() &lt; lst:startTime">
                Start time if specified must be in the future.
            </assert>
        </rule>
        <rule context="lst:listingDetail/lst:listingFormat">
            <assert test="lst:formatType='AUCTION'">
                Listing Format Type must be AUCTION.
            </assert>
        </rule>  
        <rule context="lst:listingDetail/lst:listingFormat[lst:formatType='AUCTION']/lst:stdAuctionFormat">
            <assert test="auc:startingPrice &gt; 0.00">
                Starting price must be greater than zero.
            </assert>
        </rule>     
        <rule context="lst:listingDetail/lst:listingFormat[lst:formatType='AUCTION']/lst:stdAuctionFormat[string-length(auc:reservePrice) &gt; 0]">
            <assert test="auc:reservePrice &gt; 50.00">
                Reserve price if specified must be greater than $50.
            </assert>
            <assert test="auc:reservePrice &gt; auc:startingPrice">
                Reserve price if specified must be greater than starting price.
            </assert>
        </rule>
    </pattern>
</schema>