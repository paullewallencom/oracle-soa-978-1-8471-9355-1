/* DO NOT EDIT THIS FILE - This file was generated by     */
/* Oracle JAXB Class Generator Implementation.            */
/* Any Modification to this file will be lost upon        */
/* recompilation of source schema                         */ 

package com.packtpub.schemas.obay.auc;


public class AuctionStatusImpl extends oracle.xml.jaxb.JaxbNode implements com.packtpub.schemas.obay.auc.AuctionStatus
{
   public AuctionStatusImpl()
   {
      super();
   }

   public AuctionStatusImpl(oracle.xml.parser.v2.XMLDocument ownerDoc)
   {
      super("auctionStatus", "http://schemas.packtpub.com/obay/auc", ownerDoc); 
   }

   public AuctionStatusImpl(java.lang.String name, java.lang.String namespace, oracle.xml.parser.v2.XMLDocument ownerDoc)
   {
      super(name, namespace, ownerDoc);
   }

   public AuctionStatusImpl(oracle.xml.parser.v2.XMLElement node)
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

   static final Object[] _AuctionStatus = 
   {};

   protected Object[] getSchemaObject()
   {
      return _AuctionStatus;
   }

}
