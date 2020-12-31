package PayrollDatabaseService;

import java.util.Date;

import oracle.toplink.indirection.ValueHolder;
import oracle.toplink.indirection.ValueHolderInterface;

public class Payrollitem {

    /**Map batch <-> PayrollDatabaseService.Batch
     * @associates <{PayrollDatabaseService.Batch}>
     */
    private ValueHolderInterface batch;

    /**Map employee <-> PayrollDatabaseService.Employee
     * @associates <{PayrollDatabaseService.Employee}>
     */
    private ValueHolderInterface employee;
    private String employeeid;
    private Date paymentdate;
    private String paymentamount;
    private String batchid;


}
