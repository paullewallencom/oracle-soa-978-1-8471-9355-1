/* DO NOT EDIT THIS FILE - This file was generated by     */
/* Oracle JAXB Class Generator Implementation.            */
/* Any Modification to this file will be lost upon        */
/* recompilation of source schema                         */ 

package com.packtpub.schemas.obay.auc;


public class ReservePriceImpl extends oracle.xml.jaxb.JaxbNode implements com.packtpub.schemas.obay.auc.ReservePrice
{
   public ReservePriceImpl()
   {
      super();
   }

   public ReservePriceImpl(oracle.xml.parser.v2.XMLDocument ownerDoc)
   {
      super("reservePrice", "http://schemas.packtpub.com/obay/auc", ownerDoc); 
   }

   public ReservePriceImpl(java.lang.String name, java.lang.String namespace, oracle.xml.parser.v2.XMLDocument ownerDoc)
   {
      super(name, namespace, ownerDoc);
   }

   public ReservePriceImpl(oracle.xml.parser.v2.XMLElement node)
   {
      super(node);
   }

   public void setValue(double value)
   {
      super.setJaxbDoubleValue(value);
   }

   public double getValue()
   {
      return super.getJaxbDoubleValue();
   }

   static final Object[] _ReservePrice = 
   {};

   protected Object[] getSchemaObject()
   {
      return _ReservePrice;
   }

}
