/* DO NOT EDIT THIS FILE - This file was generated by     */
/* Oracle JAXB Class Generator Implementation.            */
/* Any Modification to this file will be lost upon        */
/* recompilation of source schema                         */ 

package com.packtpub.schemas.leaverequest;


public class TLeaveRequestImpl extends oracle.xml.jaxb.JaxbNode implements com.packtpub.schemas.leaverequest.TLeaveRequest
{
   public TLeaveRequestImpl()
   {
      super();
   }

   public TLeaveRequestImpl(oracle.xml.parser.v2.XMLDocument ownerDoc)
   {
      super("tLeaveRequest", "http://schemas.packtpub.com/LeaveRequest", ownerDoc); 
   }

   public TLeaveRequestImpl(java.lang.String name, java.lang.String namespace, oracle.xml.parser.v2.XMLDocument ownerDoc)
   {
      super(name, namespace, ownerDoc);
   }

   public TLeaveRequestImpl(oracle.xml.parser.v2.XMLElement node)
   {
      super(node);
   }

   public void setEmployeeId(java.lang.String value)
   {
      java.lang.String lexval = oracle.xml.jaxb.JaxbDatatypeConverter.printString(value);
      super.setJaxbElement("employeeId", "http://schemas.packtpub.com/LeaveRequest", lexval, 0);
   }

   public java.lang.String getEmployeeId()
   {
      java.lang.String lexval = super.getJaxbElement("employeeId", "http://schemas.packtpub.com/LeaveRequest", 0);
      return oracle.xml.jaxb.JaxbDatatypeConverter.parseString(lexval);
   }

   public void setFullName(java.lang.String value)
   {
      java.lang.String lexval = oracle.xml.jaxb.JaxbDatatypeConverter.printString(value);
      super.setJaxbElement("fullName", "http://schemas.packtpub.com/LeaveRequest", lexval, 1);
   }

   public java.lang.String getFullName()
   {
      java.lang.String lexval = super.getJaxbElement("fullName", "http://schemas.packtpub.com/LeaveRequest", 1);
      return oracle.xml.jaxb.JaxbDatatypeConverter.parseString(lexval);
   }

   public void setStartDate(java.util.Calendar value)
   {
      java.lang.String lexval = oracle.xml.jaxb.JaxbDatatypeConverter.printDate(value);
      super.setJaxbElement("startDate", "http://schemas.packtpub.com/LeaveRequest", lexval, 2);
   }

   public java.util.Calendar getStartDate()
   {
      java.lang.String lexval = super.getJaxbElement("startDate", "http://schemas.packtpub.com/LeaveRequest", 2);
      return oracle.xml.jaxb.JaxbDatatypeConverter.parseDate(lexval);
   }

   public void setEndDate(java.util.Calendar value)
   {
      java.lang.String lexval = oracle.xml.jaxb.JaxbDatatypeConverter.printDate(value);
      super.setJaxbElement("endDate", "http://schemas.packtpub.com/LeaveRequest", lexval, 3);
   }

   public java.util.Calendar getEndDate()
   {
      java.lang.String lexval = super.getJaxbElement("endDate", "http://schemas.packtpub.com/LeaveRequest", 3);
      return oracle.xml.jaxb.JaxbDatatypeConverter.parseDate(lexval);
   }

   public void setLeaveType(java.lang.String value)
   {
      java.lang.String lexval = oracle.xml.jaxb.JaxbDatatypeConverter.printString(value);
      super.setJaxbElement("leaveType", "http://schemas.packtpub.com/LeaveRequest", lexval, 4);
   }

   public java.lang.String getLeaveType()
   {
      java.lang.String lexval = super.getJaxbElement("leaveType", "http://schemas.packtpub.com/LeaveRequest", 4);
      return oracle.xml.jaxb.JaxbDatatypeConverter.parseString(lexval);
   }

   public void setLeaveReason(java.lang.String value)
   {
      java.lang.String lexval = oracle.xml.jaxb.JaxbDatatypeConverter.printString(value);
      super.setJaxbElement("leaveReason", "http://schemas.packtpub.com/LeaveRequest", lexval, 5);
   }

   public java.lang.String getLeaveReason()
   {
      java.lang.String lexval = super.getJaxbElement("leaveReason", "http://schemas.packtpub.com/LeaveRequest", 5);
      return oracle.xml.jaxb.JaxbDatatypeConverter.parseString(lexval);
   }

   public void setRequestStatus(java.lang.String value)
   {
      java.lang.String lexval = oracle.xml.jaxb.JaxbDatatypeConverter.printString(value);
      super.setJaxbElement("requestStatus", "http://schemas.packtpub.com/LeaveRequest", lexval, 6);
   }

   public java.lang.String getRequestStatus()
   {
      java.lang.String lexval = super.getJaxbElement("requestStatus", "http://schemas.packtpub.com/LeaveRequest", 6);
      return oracle.xml.jaxb.JaxbDatatypeConverter.parseString(lexval);
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
            if (name.equals("employeeId") && namespace.equals("http://schemas.packtpub.com/LeaveRequest"))
            {
                  super.setNodeArrayValue(0, n);
            }

         }
         if (name != null)
         {
            if (name.equals("fullName") && namespace.equals("http://schemas.packtpub.com/LeaveRequest"))
            {
                  super.setNodeArrayValue(1, n);
            }

         }
         if (name != null)
         {
            if (name.equals("startDate") && namespace.equals("http://schemas.packtpub.com/LeaveRequest"))
            {
                  super.setNodeArrayValue(2, n);
            }

         }
         if (name != null)
         {
            if (name.equals("endDate") && namespace.equals("http://schemas.packtpub.com/LeaveRequest"))
            {
                  super.setNodeArrayValue(3, n);
            }

         }
         if (name != null)
         {
            if (name.equals("leaveType") && namespace.equals("http://schemas.packtpub.com/LeaveRequest"))
            {
                  super.setNodeArrayValue(4, n);
            }

         }
         if (name != null)
         {
            if (name.equals("leaveReason") && namespace.equals("http://schemas.packtpub.com/LeaveRequest"))
            {
                  super.setNodeArrayValue(5, n);
            }

         }
         if (name != null)
         {
            if (name.equals("requestStatus") && namespace.equals("http://schemas.packtpub.com/LeaveRequest"))
            {
                  super.setNodeArrayValue(6, n);
            }

         }
         n = (oracle.xml.parser.v2.XMLNode)n.getNextSibling();
         i++;
      }

      super.populateNodeArray(node);
   }

   static final Object[] _TLeaveRequest = 
   {"employeeId", "fullName", "startDate", "endDate", "leaveType", "leaveReason", "requestStatus"};

   protected Object[] getSchemaObject()
   {
      return _TLeaveRequest;
   }

}