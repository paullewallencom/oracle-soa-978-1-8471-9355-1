/* DO NOT EDIT THIS FILE - This file was generated by     */
/* Oracle JAXB Class Generator Implementation.            */
/* Any Modification to this file will be lost upon        */
/* recompilation of source schema                         */ 

package com.packtpub.schemas.obay.auc;


public class BidIdImpl extends oracle.xml.jaxb.JaxbNode implements com.packtpub.schemas.obay.auc.BidId
{
   public BidIdImpl()
   {
      super();
   }

   public BidIdImpl(oracle.xml.parser.v2.XMLDocument ownerDoc)
   {
      super("bidId", "http://schemas.packtpub.com/obay/auc", ownerDoc); 
   }

   public BidIdImpl(java.lang.String name, java.lang.String namespace, oracle.xml.parser.v2.XMLDocument ownerDoc)
   {
      super(name, namespace, ownerDoc);
   }

   public BidIdImpl(oracle.xml.parser.v2.XMLElement node)
   {
      super(node);
   }

   public void setValue(java.lang.String value)
   {
      super.setJaxbStringValue(value);
   }

   public java.lang.String getValue()
   {
      return super.getJaxbStringValue();
   }

   static final Object[] _BidId = 
   {};

   protected Object[] getSchemaObject()
   {
      return _BidId;
   }

}
