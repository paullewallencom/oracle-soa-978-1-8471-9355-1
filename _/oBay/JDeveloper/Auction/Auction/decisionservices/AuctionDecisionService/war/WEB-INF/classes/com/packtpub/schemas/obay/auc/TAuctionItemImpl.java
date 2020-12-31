/* DO NOT EDIT THIS FILE - This file was generated by     */
/* Oracle JAXB Class Generator Implementation.            */
/* Any Modification to this file will be lost upon        */
/* recompilation of source schema                         */ 

package com.packtpub.schemas.obay.auc;


public class TAuctionItemImpl extends oracle.xml.jaxb.JaxbNode implements com.packtpub.schemas.obay.auc.TAuctionItem
{
   public TAuctionItemImpl()
   {
      super();
   }

   public TAuctionItemImpl(oracle.xml.parser.v2.XMLDocument ownerDoc)
   {
      super("tAuctionItem", "http://schemas.packtpub.com/obay/auc", ownerDoc); 
   }

   public TAuctionItemImpl(java.lang.String name, java.lang.String namespace, oracle.xml.parser.v2.XMLDocument ownerDoc)
   {
      super(name, namespace, ownerDoc);
   }

   public TAuctionItemImpl(oracle.xml.parser.v2.XMLElement node)
   {
      super(node);
   }

   public void setAuctionType(java.lang.String value)
   {
      java.lang.String lexval = oracle.xml.jaxb.JaxbDatatypeConverter.printString(value);
      super.setJaxbElement("auctionType", "http://schemas.packtpub.com/obay/auc", lexval, 0);
   }

   public java.lang.String getAuctionType()
   {
      java.lang.String lexval = super.getJaxbElement("auctionType", "http://schemas.packtpub.com/obay/auc", 0);
      return oracle.xml.jaxb.JaxbDatatypeConverter.parseString(lexval);
   }

   public void setStartTime(java.util.Calendar value)
   {
      java.lang.String lexval = oracle.xml.jaxb.JaxbDatatypeConverter.printDateTime(value);
      super.setJaxbElement("startTime", "http://schemas.packtpub.com/obay/auc", lexval, 1);
   }

   public java.util.Calendar getStartTime()
   {
      java.lang.String lexval = super.getJaxbElement("startTime", "http://schemas.packtpub.com/obay/auc", 1);
      return oracle.xml.jaxb.JaxbDatatypeConverter.parseDateTime(lexval);
   }

   public void setEndTime(java.util.Calendar value)
   {
      java.lang.String lexval = oracle.xml.jaxb.JaxbDatatypeConverter.printDateTime(value);
      super.setJaxbElement("endTime", "http://schemas.packtpub.com/obay/auc", lexval, 2);
   }

   public java.util.Calendar getEndTime()
   {
      java.lang.String lexval = super.getJaxbElement("endTime", "http://schemas.packtpub.com/obay/auc", 2);
      return oracle.xml.jaxb.JaxbDatatypeConverter.parseDateTime(lexval);
   }

   public void setStartingPrice(double value)
   {
      java.lang.String lexval = oracle.xml.jaxb.JaxbDatatypeConverter.printDouble(value);
      super.setJaxbElement("startingPrice", "http://schemas.packtpub.com/obay/auc", lexval, 3);
   }

   public double getStartingPrice()
   {
      java.lang.String lexval = super.getJaxbElement("startingPrice", "http://schemas.packtpub.com/obay/auc", 3);
      return oracle.xml.jaxb.JaxbDatatypeConverter.parseDouble(lexval);
   }

   public void setReservePrice(double value)
   {
      java.lang.String lexval = oracle.xml.jaxb.JaxbDatatypeConverter.printDouble(value);
      super.setJaxbElement("reservePrice", "http://schemas.packtpub.com/obay/auc", lexval, 4);
   }

   public double getReservePrice()
   {
      java.lang.String lexval = super.getJaxbElement("reservePrice", "http://schemas.packtpub.com/obay/auc", 4);
      return oracle.xml.jaxb.JaxbDatatypeConverter.parseDouble(lexval);
   }

   public void setWinningPrice(double value)
   {
      java.lang.String lexval = oracle.xml.jaxb.JaxbDatatypeConverter.printDouble(value);
      super.setJaxbElement("winningPrice", "http://schemas.packtpub.com/obay/auc", lexval, 5);
   }

   public double getWinningPrice()
   {
      java.lang.String lexval = super.getJaxbElement("winningPrice", "http://schemas.packtpub.com/obay/auc", 5);
      return oracle.xml.jaxb.JaxbDatatypeConverter.parseDouble(lexval);
   }

   public void setWinningBid(com.packtpub.schemas.obay.auc.TBid value)
   {
      super.setElement("winningBid", "http://schemas.packtpub.com/obay/auc", (oracle.xml.jaxb.JaxbNode)value, 6);
   }

   public com.packtpub.schemas.obay.auc.TBid getWinningBid()
   {
      com.packtpub.schemas.obay.auc.TBidImpl obj = new com.packtpub.schemas.obay.auc.TBidImpl(getOwnerDocument());
      return (com.packtpub.schemas.obay.auc.TBid) super.getElement("winningBid", "http://schemas.packtpub.com/obay/auc", (oracle.xml.jaxb.JaxbNode)obj, 6);
   }

   public void setBidHistory(com.packtpub.schemas.obay.auc.TBids value)
   {
      super.setElement("bidHistory", "http://schemas.packtpub.com/obay/auc", (oracle.xml.jaxb.JaxbNode)value, 7);
   }

   public com.packtpub.schemas.obay.auc.TBids getBidHistory()
   {
      com.packtpub.schemas.obay.auc.TBidsImpl obj = new com.packtpub.schemas.obay.auc.TBidsImpl(getOwnerDocument());
      return (com.packtpub.schemas.obay.auc.TBids) super.getElement("bidHistory", "http://schemas.packtpub.com/obay/auc", (oracle.xml.jaxb.JaxbNode)obj, 7);
   }

   public void populateNodeArray(oracle.xml.parser.v2.XMLNode node)
   {
      java.lang.String name, namespace;
      oracle.xml.parser.v2.XMLNode n = (oracle.xml.parser.v2.XMLNode)node.getFirstChild();

      int i = 0;
      while (n != null)
      {
         name = n.getLocalName(); 
         namespace = n.getNamespaceURI();

         if (name == null)
         {
            n = (oracle.xml.parser.v2.XMLNode)n.getNextSibling();
            i++;
            continue;
         }

         if (namespace == null)
            namespace = "";

         if (name != null)
         {
            if (name.equals("auctionType") && namespace.equals("http://schemas.packtpub.com/obay/auc"))
            {
                  super.setNodeArrayValue(0, n);
            }

         }
         if (name != null)
         {
            if (name.equals("startTime") && namespace.equals("http://schemas.packtpub.com/obay/auc"))
            {
                  super.setNodeArrayValue(1, n);
            }

         }
         if (name != null)
         {
            if (name.equals("endTime") && namespace.equals("http://schemas.packtpub.com/obay/auc"))
            {
                  super.setNodeArrayValue(2, n);
            }

         }
         if (name != null)
         {
            if (name.equals("startingPrice") && namespace.equals("http://schemas.packtpub.com/obay/auc"))
            {
                  super.setNodeArrayValue(3, n);
            }

         }
         if (name != null)
         {
            if (name.equals("reservePrice") && namespace.equals("http://schemas.packtpub.com/obay/auc"))
            {
                  super.setNodeArrayValue(4, n);
            }

         }
         if (name != null)
         {
            if (name.equals("winningPrice") && namespace.equals("http://schemas.packtpub.com/obay/auc"))
            {
                  super.setNodeArrayValue(5, n);
            }

         }
         if (name != null)
         {
            if (name.equals("winningBid") && namespace.equals("http://schemas.packtpub.com/obay/auc"))
            {
                  super.setNodeArrayValue(6, n);
            }

         }
         if (name != null)
         {
            if (name.equals("bidHistory") && namespace.equals("http://schemas.packtpub.com/obay/auc"))
            {
                  super.setNodeArrayValue(7, n);
            }

         }
         n = (oracle.xml.parser.v2.XMLNode)n.getNextSibling();
         i++;
      }

      super.populateNodeArray(node);
   }

   static final Object[] _TAuctionItem = 
   {"auctionType", "startTime", "endTime", "startingPrice", "reservePrice", "winningPrice", "winningBid", "bidHistory"};

   protected Object[] getSchemaObject()
   {
      return _TAuctionItem;
   }

}