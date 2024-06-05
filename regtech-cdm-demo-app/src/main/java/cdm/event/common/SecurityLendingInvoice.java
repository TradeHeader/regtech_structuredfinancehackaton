package cdm.event.common;

import cdm.base.staticdata.party.Party;
import cdm.event.common.BillingRecord;
import cdm.event.common.BillingSummary;
import cdm.event.common.SecurityLendingInvoice;
import cdm.event.common.SecurityLendingInvoice.SecurityLendingInvoiceBuilder;
import cdm.event.common.SecurityLendingInvoice.SecurityLendingInvoiceBuilderImpl;
import cdm.event.common.SecurityLendingInvoice.SecurityLendingInvoiceImpl;
import cdm.event.common.meta.SecurityLendingInvoiceMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Specifies the information required for inclusion in a securities lending billing invoice.
 * @version ${project.version}
 */
@RosettaDataType(value="SecurityLendingInvoice", builder=SecurityLendingInvoice.SecurityLendingInvoiceBuilderImpl.class, version="${project.version}")
public interface SecurityLendingInvoice extends RosettaModelObject, GlobalKey {

	SecurityLendingInvoiceMeta metaData = new SecurityLendingInvoiceMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The party issuing the invoice
	 */
	Party getSendingParty();
	/**
	 * The party receiving the invoice
	 */
	Party getReceivingParty();
	/**
	 * The starting date of the period described by this invoice
	 */
	Date getBillingStartDate();
	/**
	 * The ending date of the period described by this invoice
	 */
	Date getBillingEndDate();
	/**
	 * The billing records contained within the invoice
	 */
	List<? extends BillingRecord> getBillingRecord();
	/**
	 * The billing summaries contained within the invoice
	 */
	List<? extends BillingSummary> getBillingSummary();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	SecurityLendingInvoice build();
	
	SecurityLendingInvoice.SecurityLendingInvoiceBuilder toBuilder();
	
	static SecurityLendingInvoice.SecurityLendingInvoiceBuilder builder() {
		return new SecurityLendingInvoice.SecurityLendingInvoiceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SecurityLendingInvoice> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SecurityLendingInvoice> getType() {
		return SecurityLendingInvoice.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("sendingParty"), processor, Party.class, getSendingParty());
		processRosetta(path.newSubPath("receivingParty"), processor, Party.class, getReceivingParty());
		processor.processBasic(path.newSubPath("billingStartDate"), Date.class, getBillingStartDate(), this);
		processor.processBasic(path.newSubPath("billingEndDate"), Date.class, getBillingEndDate(), this);
		processRosetta(path.newSubPath("billingRecord"), processor, BillingRecord.class, getBillingRecord());
		processRosetta(path.newSubPath("billingSummary"), processor, BillingSummary.class, getBillingSummary());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface SecurityLendingInvoiceBuilder extends SecurityLendingInvoice, RosettaModelObjectBuilder {
		Party.PartyBuilder getOrCreateSendingParty();
		Party.PartyBuilder getSendingParty();
		Party.PartyBuilder getOrCreateReceivingParty();
		Party.PartyBuilder getReceivingParty();
		BillingRecord.BillingRecordBuilder getOrCreateBillingRecord(int _index);
		List<? extends BillingRecord.BillingRecordBuilder> getBillingRecord();
		BillingSummary.BillingSummaryBuilder getOrCreateBillingSummary(int _index);
		List<? extends BillingSummary.BillingSummaryBuilder> getBillingSummary();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		SecurityLendingInvoice.SecurityLendingInvoiceBuilder setSendingParty(Party sendingParty);
		SecurityLendingInvoice.SecurityLendingInvoiceBuilder setReceivingParty(Party receivingParty);
		SecurityLendingInvoice.SecurityLendingInvoiceBuilder setBillingStartDate(Date billingStartDate);
		SecurityLendingInvoice.SecurityLendingInvoiceBuilder setBillingEndDate(Date billingEndDate);
		SecurityLendingInvoice.SecurityLendingInvoiceBuilder addBillingRecord(BillingRecord billingRecord0);
		SecurityLendingInvoice.SecurityLendingInvoiceBuilder addBillingRecord(BillingRecord billingRecord1, int _idx);
		SecurityLendingInvoice.SecurityLendingInvoiceBuilder addBillingRecord(List<? extends BillingRecord> billingRecord2);
		SecurityLendingInvoice.SecurityLendingInvoiceBuilder setBillingRecord(List<? extends BillingRecord> billingRecord3);
		SecurityLendingInvoice.SecurityLendingInvoiceBuilder addBillingSummary(BillingSummary billingSummary0);
		SecurityLendingInvoice.SecurityLendingInvoiceBuilder addBillingSummary(BillingSummary billingSummary1, int _idx);
		SecurityLendingInvoice.SecurityLendingInvoiceBuilder addBillingSummary(List<? extends BillingSummary> billingSummary2);
		SecurityLendingInvoice.SecurityLendingInvoiceBuilder setBillingSummary(List<? extends BillingSummary> billingSummary3);
		SecurityLendingInvoice.SecurityLendingInvoiceBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("sendingParty"), processor, Party.PartyBuilder.class, getSendingParty());
			processRosetta(path.newSubPath("receivingParty"), processor, Party.PartyBuilder.class, getReceivingParty());
			processor.processBasic(path.newSubPath("billingStartDate"), Date.class, getBillingStartDate(), this);
			processor.processBasic(path.newSubPath("billingEndDate"), Date.class, getBillingEndDate(), this);
			processRosetta(path.newSubPath("billingRecord"), processor, BillingRecord.BillingRecordBuilder.class, getBillingRecord());
			processRosetta(path.newSubPath("billingSummary"), processor, BillingSummary.BillingSummaryBuilder.class, getBillingSummary());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		SecurityLendingInvoice.SecurityLendingInvoiceBuilder prune();
	}

	/*********************** Immutable Implementation of SecurityLendingInvoice  ***********************/
	class SecurityLendingInvoiceImpl implements SecurityLendingInvoice {
		private final Party sendingParty;
		private final Party receivingParty;
		private final Date billingStartDate;
		private final Date billingEndDate;
		private final List<? extends BillingRecord> billingRecord;
		private final List<? extends BillingSummary> billingSummary;
		private final MetaFields meta;
		
		protected SecurityLendingInvoiceImpl(SecurityLendingInvoice.SecurityLendingInvoiceBuilder builder) {
			this.sendingParty = ofNullable(builder.getSendingParty()).map(f->f.build()).orElse(null);
			this.receivingParty = ofNullable(builder.getReceivingParty()).map(f->f.build()).orElse(null);
			this.billingStartDate = builder.getBillingStartDate();
			this.billingEndDate = builder.getBillingEndDate();
			this.billingRecord = ofNullable(builder.getBillingRecord()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.billingSummary = ofNullable(builder.getBillingSummary()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("sendingParty")
		public Party getSendingParty() {
			return sendingParty;
		}
		
		@Override
		@RosettaAttribute("receivingParty")
		public Party getReceivingParty() {
			return receivingParty;
		}
		
		@Override
		@RosettaAttribute("billingStartDate")
		public Date getBillingStartDate() {
			return billingStartDate;
		}
		
		@Override
		@RosettaAttribute("billingEndDate")
		public Date getBillingEndDate() {
			return billingEndDate;
		}
		
		@Override
		@RosettaAttribute("billingRecord")
		public List<? extends BillingRecord> getBillingRecord() {
			return billingRecord;
		}
		
		@Override
		@RosettaAttribute("billingSummary")
		public List<? extends BillingSummary> getBillingSummary() {
			return billingSummary;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public SecurityLendingInvoice build() {
			return this;
		}
		
		@Override
		public SecurityLendingInvoice.SecurityLendingInvoiceBuilder toBuilder() {
			SecurityLendingInvoice.SecurityLendingInvoiceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SecurityLendingInvoice.SecurityLendingInvoiceBuilder builder) {
			ofNullable(getSendingParty()).ifPresent(builder::setSendingParty);
			ofNullable(getReceivingParty()).ifPresent(builder::setReceivingParty);
			ofNullable(getBillingStartDate()).ifPresent(builder::setBillingStartDate);
			ofNullable(getBillingEndDate()).ifPresent(builder::setBillingEndDate);
			ofNullable(getBillingRecord()).ifPresent(builder::setBillingRecord);
			ofNullable(getBillingSummary()).ifPresent(builder::setBillingSummary);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SecurityLendingInvoice _that = getType().cast(o);
		
			if (!Objects.equals(sendingParty, _that.getSendingParty())) return false;
			if (!Objects.equals(receivingParty, _that.getReceivingParty())) return false;
			if (!Objects.equals(billingStartDate, _that.getBillingStartDate())) return false;
			if (!Objects.equals(billingEndDate, _that.getBillingEndDate())) return false;
			if (!ListEquals.listEquals(billingRecord, _that.getBillingRecord())) return false;
			if (!ListEquals.listEquals(billingSummary, _that.getBillingSummary())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (sendingParty != null ? sendingParty.hashCode() : 0);
			_result = 31 * _result + (receivingParty != null ? receivingParty.hashCode() : 0);
			_result = 31 * _result + (billingStartDate != null ? billingStartDate.hashCode() : 0);
			_result = 31 * _result + (billingEndDate != null ? billingEndDate.hashCode() : 0);
			_result = 31 * _result + (billingRecord != null ? billingRecord.hashCode() : 0);
			_result = 31 * _result + (billingSummary != null ? billingSummary.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SecurityLendingInvoice {" +
				"sendingParty=" + this.sendingParty + ", " +
				"receivingParty=" + this.receivingParty + ", " +
				"billingStartDate=" + this.billingStartDate + ", " +
				"billingEndDate=" + this.billingEndDate + ", " +
				"billingRecord=" + this.billingRecord + ", " +
				"billingSummary=" + this.billingSummary + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of SecurityLendingInvoice  ***********************/
	class SecurityLendingInvoiceBuilderImpl implements SecurityLendingInvoice.SecurityLendingInvoiceBuilder, GlobalKeyBuilder {
	
		protected Party.PartyBuilder sendingParty;
		protected Party.PartyBuilder receivingParty;
		protected Date billingStartDate;
		protected Date billingEndDate;
		protected List<BillingRecord.BillingRecordBuilder> billingRecord = new ArrayList<>();
		protected List<BillingSummary.BillingSummaryBuilder> billingSummary = new ArrayList<>();
		protected MetaFields.MetaFieldsBuilder meta;
	
		public SecurityLendingInvoiceBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("sendingParty")
		public Party.PartyBuilder getSendingParty() {
			return sendingParty;
		}
		
		@Override
		public Party.PartyBuilder getOrCreateSendingParty() {
			Party.PartyBuilder result;
			if (sendingParty!=null) {
				result = sendingParty;
			}
			else {
				result = sendingParty = Party.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("receivingParty")
		public Party.PartyBuilder getReceivingParty() {
			return receivingParty;
		}
		
		@Override
		public Party.PartyBuilder getOrCreateReceivingParty() {
			Party.PartyBuilder result;
			if (receivingParty!=null) {
				result = receivingParty;
			}
			else {
				result = receivingParty = Party.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("billingStartDate")
		public Date getBillingStartDate() {
			return billingStartDate;
		}
		
		@Override
		@RosettaAttribute("billingEndDate")
		public Date getBillingEndDate() {
			return billingEndDate;
		}
		
		@Override
		@RosettaAttribute("billingRecord")
		public List<? extends BillingRecord.BillingRecordBuilder> getBillingRecord() {
			return billingRecord;
		}
		
		public BillingRecord.BillingRecordBuilder getOrCreateBillingRecord(int _index) {
		
			if (billingRecord==null) {
				this.billingRecord = new ArrayList<>();
			}
			BillingRecord.BillingRecordBuilder result;
			return getIndex(billingRecord, _index, () -> {
						BillingRecord.BillingRecordBuilder newBillingRecord = BillingRecord.builder();
						return newBillingRecord;
					});
		}
		
		@Override
		@RosettaAttribute("billingSummary")
		public List<? extends BillingSummary.BillingSummaryBuilder> getBillingSummary() {
			return billingSummary;
		}
		
		public BillingSummary.BillingSummaryBuilder getOrCreateBillingSummary(int _index) {
		
			if (billingSummary==null) {
				this.billingSummary = new ArrayList<>();
			}
			BillingSummary.BillingSummaryBuilder result;
			return getIndex(billingSummary, _index, () -> {
						BillingSummary.BillingSummaryBuilder newBillingSummary = BillingSummary.builder();
						return newBillingSummary;
					});
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("sendingParty")
		public SecurityLendingInvoice.SecurityLendingInvoiceBuilder setSendingParty(Party sendingParty) {
			this.sendingParty = sendingParty==null?null:sendingParty.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("receivingParty")
		public SecurityLendingInvoice.SecurityLendingInvoiceBuilder setReceivingParty(Party receivingParty) {
			this.receivingParty = receivingParty==null?null:receivingParty.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("billingStartDate")
		public SecurityLendingInvoice.SecurityLendingInvoiceBuilder setBillingStartDate(Date billingStartDate) {
			this.billingStartDate = billingStartDate==null?null:billingStartDate;
			return this;
		}
		@Override
		@RosettaAttribute("billingEndDate")
		public SecurityLendingInvoice.SecurityLendingInvoiceBuilder setBillingEndDate(Date billingEndDate) {
			this.billingEndDate = billingEndDate==null?null:billingEndDate;
			return this;
		}
		@Override
		public SecurityLendingInvoice.SecurityLendingInvoiceBuilder addBillingRecord(BillingRecord billingRecord) {
			if (billingRecord!=null) this.billingRecord.add(billingRecord.toBuilder());
			return this;
		}
		
		@Override
		public SecurityLendingInvoice.SecurityLendingInvoiceBuilder addBillingRecord(BillingRecord billingRecord, int _idx) {
			getIndex(this.billingRecord, _idx, () -> billingRecord.toBuilder());
			return this;
		}
		@Override 
		public SecurityLendingInvoice.SecurityLendingInvoiceBuilder addBillingRecord(List<? extends BillingRecord> billingRecords) {
			if (billingRecords != null) {
				for (BillingRecord toAdd : billingRecords) {
					this.billingRecord.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("billingRecord")
		public SecurityLendingInvoice.SecurityLendingInvoiceBuilder setBillingRecord(List<? extends BillingRecord> billingRecords) {
			if (billingRecords == null)  {
				this.billingRecord = new ArrayList<>();
			}
			else {
				this.billingRecord = billingRecords.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public SecurityLendingInvoice.SecurityLendingInvoiceBuilder addBillingSummary(BillingSummary billingSummary) {
			if (billingSummary!=null) this.billingSummary.add(billingSummary.toBuilder());
			return this;
		}
		
		@Override
		public SecurityLendingInvoice.SecurityLendingInvoiceBuilder addBillingSummary(BillingSummary billingSummary, int _idx) {
			getIndex(this.billingSummary, _idx, () -> billingSummary.toBuilder());
			return this;
		}
		@Override 
		public SecurityLendingInvoice.SecurityLendingInvoiceBuilder addBillingSummary(List<? extends BillingSummary> billingSummarys) {
			if (billingSummarys != null) {
				for (BillingSummary toAdd : billingSummarys) {
					this.billingSummary.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("billingSummary")
		public SecurityLendingInvoice.SecurityLendingInvoiceBuilder setBillingSummary(List<? extends BillingSummary> billingSummarys) {
			if (billingSummarys == null)  {
				this.billingSummary = new ArrayList<>();
			}
			else {
				this.billingSummary = billingSummarys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("meta")
		public SecurityLendingInvoice.SecurityLendingInvoiceBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public SecurityLendingInvoice build() {
			return new SecurityLendingInvoice.SecurityLendingInvoiceImpl(this);
		}
		
		@Override
		public SecurityLendingInvoice.SecurityLendingInvoiceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SecurityLendingInvoice.SecurityLendingInvoiceBuilder prune() {
			if (sendingParty!=null && !sendingParty.prune().hasData()) sendingParty = null;
			if (receivingParty!=null && !receivingParty.prune().hasData()) receivingParty = null;
			billingRecord = billingRecord.stream().filter(b->b!=null).<BillingRecord.BillingRecordBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			billingSummary = billingSummary.stream().filter(b->b!=null).<BillingSummary.BillingSummaryBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSendingParty()!=null && getSendingParty().hasData()) return true;
			if (getReceivingParty()!=null && getReceivingParty().hasData()) return true;
			if (getBillingStartDate()!=null) return true;
			if (getBillingEndDate()!=null) return true;
			if (getBillingRecord()!=null && getBillingRecord().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getBillingSummary()!=null && getBillingSummary().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SecurityLendingInvoice.SecurityLendingInvoiceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			SecurityLendingInvoice.SecurityLendingInvoiceBuilder o = (SecurityLendingInvoice.SecurityLendingInvoiceBuilder) other;
			
			merger.mergeRosetta(getSendingParty(), o.getSendingParty(), this::setSendingParty);
			merger.mergeRosetta(getReceivingParty(), o.getReceivingParty(), this::setReceivingParty);
			merger.mergeRosetta(getBillingRecord(), o.getBillingRecord(), this::getOrCreateBillingRecord);
			merger.mergeRosetta(getBillingSummary(), o.getBillingSummary(), this::getOrCreateBillingSummary);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getBillingStartDate(), o.getBillingStartDate(), this::setBillingStartDate);
			merger.mergeBasic(getBillingEndDate(), o.getBillingEndDate(), this::setBillingEndDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SecurityLendingInvoice _that = getType().cast(o);
		
			if (!Objects.equals(sendingParty, _that.getSendingParty())) return false;
			if (!Objects.equals(receivingParty, _that.getReceivingParty())) return false;
			if (!Objects.equals(billingStartDate, _that.getBillingStartDate())) return false;
			if (!Objects.equals(billingEndDate, _that.getBillingEndDate())) return false;
			if (!ListEquals.listEquals(billingRecord, _that.getBillingRecord())) return false;
			if (!ListEquals.listEquals(billingSummary, _that.getBillingSummary())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (sendingParty != null ? sendingParty.hashCode() : 0);
			_result = 31 * _result + (receivingParty != null ? receivingParty.hashCode() : 0);
			_result = 31 * _result + (billingStartDate != null ? billingStartDate.hashCode() : 0);
			_result = 31 * _result + (billingEndDate != null ? billingEndDate.hashCode() : 0);
			_result = 31 * _result + (billingRecord != null ? billingRecord.hashCode() : 0);
			_result = 31 * _result + (billingSummary != null ? billingSummary.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SecurityLendingInvoiceBuilder {" +
				"sendingParty=" + this.sendingParty + ", " +
				"receivingParty=" + this.receivingParty + ", " +
				"billingStartDate=" + this.billingStartDate + ", " +
				"billingEndDate=" + this.billingEndDate + ", " +
				"billingRecord=" + this.billingRecord + ", " +
				"billingSummary=" + this.billingSummary + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
