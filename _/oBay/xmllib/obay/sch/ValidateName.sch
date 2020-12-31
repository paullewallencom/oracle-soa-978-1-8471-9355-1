<sch:schema xmlns:sch="http://www.ascc.net/xml/schematron">
    <!-- define the process namespace for finding the elements in the rule-->
    <sch:ns uri="http://xmlns.oracle.com/BPELSchematronValidationSample" prefix="client"/>
        <sch:pattern name="sum up item values and check them with the sum attribute of the parent"> 
            <!-- context is the super variable that can be queried for validation-->
            <sch:rule context="client:add">
                <sch:assert test="@sum = sum(client:item)">
                        The value of the SUMMMM attribute should be the sum of all the values in the item child elements.
                </sch:assert>
            </sch:rule>
        </sch:pattern> 
</sch:schema>