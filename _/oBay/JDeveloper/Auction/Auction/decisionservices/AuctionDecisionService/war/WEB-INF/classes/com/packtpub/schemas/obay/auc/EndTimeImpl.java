/* DO NOT EDIT THIS FILE - This file was generated by     */
/* Oracle JAXB Class Generator Implementation.            */
/* Any Modification to this file will be lost upon        */
/* recompilation of source schema                         */ 

package com.packtpub.schemas.obay.auc;


public class EndTimeImpl extends oracle.xml.jaxb.JaxbNode implements com.packtpub.schemas.obay.auc.EndTime
{
   public EndTimeImpl()
   {
      super();
   }

   public EndTimeImpl(oracle.xml.parser.v2.XMLDocument ownerDoc)
   {
      super("endTime", "http://schemas.packtpub.com/obay/auc", ownerDoc); 
   }

   public EndTimeImpl(java.lang.String name, java.lang.String namespace, oracle.xml.parser.v2.XMLDocument ownerDoc)
   {
      super(name, namespace, ownerDoc);
   }

   public EndTimeImpl(oracle.xml.parser.v2.XMLElement node)
   {
      super(node);
   }

   public void setValue(java.util.Calendar value)
   {
      super.setJaxbCalendarValue(value);
   }

   public java.util.Calendar getValue()
   {
      return super.getJaxbCalendarValue();
   }

   static final Object[] _EndTime = 
   {};

   protected Object[] getSchemaObject()
   {
      return _EndTime;
   }

}
