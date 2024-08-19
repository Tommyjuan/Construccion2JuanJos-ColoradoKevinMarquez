package app.dto;


public class InvoiceDetailDto {

    private long id;
    private InvoiceDto invoiceId;
    private int item;
    private String description;
    private Double amount;

    public InvoiceDetailDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public InvoiceDto getInvoiceDtoId() {
        return invoiceId;
    }

    public void setInvoiceId(InvoiceDto invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
