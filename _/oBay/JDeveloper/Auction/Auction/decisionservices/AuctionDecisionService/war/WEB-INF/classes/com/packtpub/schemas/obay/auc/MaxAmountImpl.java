/* DO NOT EDIT THIS FILE - This file was generated by     */
/* Oracle JAXB Class Generator Implementation.            */
/* Any Modification to this file will be lost upon        */
/* recompilation of source schema                         */ 

package com.packtpub.schemas.obay.auc;


public class MaxAmountImpl extends oracle.xml.jaxb.JaxbNode implements com.packtpub.schemas.obay.auc.MaxAmount
{
   public MaxAmountImpl()
   {
      super();
   }

   public MaxAmountImpl(oracle.xml.parser.v2.XMLDocument ownerDoc)
   {
      super("maxAmount", "http://schemas.packtpub.com/obay/auc", ownerDoc); 
   }

   public MaxAmountImpl(java.lang.String name, java.lang.String namespace, oracle.xml.parser.v2.XMLDocument ownerDoc)
   {
      super(name, namespace, ownerDoc);
   }

   public MaxAmountImpl(oracle.xml.parser.v2.XMLElement node)
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

   static final Object[] _MaxAmount = 
   {};

   protected Object[] getSchemaObject()
   {
      return _MaxAmount;
   }

}