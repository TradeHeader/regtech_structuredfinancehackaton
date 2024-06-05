package cdm.product.common.settlement;

import cdm.base.datetime.AdjustableDates;
import cdm.base.datetime.RelativeDateOffset;
import cdm.observable.asset.MultipleValuationDates;
import cdm.observable.asset.SingleValuationDate;
import cdm.product.common.settlement.FxFixingDate;
import cdm.product.common.settlement.ValuationDate;
import cdm.product.common.settlement.ValuationDate.ValuationDateBuilder;
import cdm.product.common.settlement.ValuationDate.ValuationDateBuilderImpl;
import cdm.product.common.settlement.ValuationDate.ValuationDateImpl;
import cdm.product.common.settlement.meta.ValuationDateMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A single object that represents the different methods to specify a valuation date, as used for cash settlement. The Single / Multiple ValuationDate is used for the determination of recovery in a credit event, the RelativeDateOffset is used for cash-settled option, and FxFixingDate is used for cross-currency settlement.
 * @version ${project.version}
 */
@RosettaDataType(value="ValuationDate", builder=ValuationDate.ValuationDateBuilderImpl.class, version="${project.version}")
public interface ValuationDate extends RosettaModelObject {

	ValuationDateMeta metaData = new ValuationDateMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Where single valuation date is specified as being applicable for cash settlement, this element specifies the number of business days after satisfaction of all conditions to settlement when such valuation date occurs. ISDA 2003 Term: Single Valuation Date.
	 */
	SingleValuationDate getSingleValuationDate();
	/**
	 * Where multiple valuation dates are specified as being applicable for cash settlement, this element specifies (a) the number of applicable valuation dates, and (b) the number of business days after satisfaction of all conditions to settlement when the first such valuation date occurs, and (c) the number of business days thereafter of each successive valuation date. ISDA 2003 Term: Multiple Valuation Dates.
	 */
	MultipleValuationDates getMultipleValuationDates();
	/**
	 * The date on which the cash settlement amount will be determined according to the cash settlement method if the parties have not otherwise been able to agree the cash settlement amount. This attribute was formerly part of &#39;OptionCashSettlement&#39;, which is now being harmonised into a common &#39;CashSettlementTerms&#39; that includes a &#39;ValuationDate&#39;.
	 */
	RelativeDateOffset getValuationDate();
	/**
	 * The date on which the currency rate will be determined for the purpose of specifying the amount in deliverable currency. This attribute was formerly part of &#39;NonDeliverableSettlement&#39;, which is now being harmonised into a common &#39;CashSettlementTerms&#39; that includes a &#39;ValuationDate&#39;.
	 */
	FxFixingDate getFxFixingDate();
	/**
	 * The date, when expressed as a schedule of date(s), on which the currency rate will be determined for the purpose of specifying the amount in deliverable currency. This attribute was formerly part of &#39;NonDeliverableSettlement&#39;, which is now being harmonised into a common &#39;CashSettlementTerms&#39; that includes a &#39;ValuationDate&#39;.
	 */
	AdjustableDates getFxFixingSchedule();

	/*********************** Build Methods  ***********************/
	ValuationDate build();
	
	ValuationDate.ValuationDateBuilder toBuilder();
	
	static ValuationDate.ValuationDateBuilder builder() {
		return new ValuationDate.ValuationDateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ValuationDate> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ValuationDate> getType() {
		return ValuationDate.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("singleValuationDate"), processor, SingleValuationDate.class, getSingleValuationDate());
		processRosetta(path.newSubPath("multipleValuationDates"), processor, MultipleValuationDates.class, getMultipleValuationDates());
		processRosetta(path.newSubPath("valuationDate"), processor, RelativeDateOffset.class, getValuationDate());
		processRosetta(path.newSubPath("fxFixingDate"), processor, FxFixingDate.class, getFxFixingDate());
		processRosetta(path.newSubPath("fxFixingSchedule"), processor, AdjustableDates.class, getFxFixingSchedule());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ValuationDateBuilder extends ValuationDate, RosettaModelObjectBuilder {
		SingleValuationDate.SingleValuationDateBuilder getOrCreateSingleValuationDate();
		SingleValuationDate.SingleValuationDateBuilder getSingleValuationDate();
		MultipleValuationDates.MultipleValuationDatesBuilder getOrCreateMultipleValuationDates();
		MultipleValuationDates.MultipleValuationDatesBuilder getMultipleValuationDates();
		RelativeDateOffset.RelativeDateOffsetBuilder getOrCreateValuationDate();
		RelativeDateOffset.RelativeDateOffsetBuilder getValuationDate();
		FxFixingDate.FxFixingDateBuilder getOrCreateFxFixingDate();
		FxFixingDate.FxFixingDateBuilder getFxFixingDate();
		AdjustableDates.AdjustableDatesBuilder getOrCreateFxFixingSchedule();
		AdjustableDates.AdjustableDatesBuilder getFxFixingSchedule();
		ValuationDate.ValuationDateBuilder setSingleValuationDate(SingleValuationDate singleValuationDate);
		ValuationDate.ValuationDateBuilder setMultipleValuationDates(MultipleValuationDates multipleValuationDates);
		ValuationDate.ValuationDateBuilder setValuationDate(RelativeDateOffset valuationDate);
		ValuationDate.ValuationDateBuilder setFxFixingDate(FxFixingDate fxFixingDate);
		ValuationDate.ValuationDateBuilder setFxFixingSchedule(AdjustableDates fxFixingSchedule);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("singleValuationDate"), processor, SingleValuationDate.SingleValuationDateBuilder.class, getSingleValuationDate());
			processRosetta(path.newSubPath("multipleValuationDates"), processor, MultipleValuationDates.MultipleValuationDatesBuilder.class, getMultipleValuationDates());
			processRosetta(path.newSubPath("valuationDate"), processor, RelativeDateOffset.RelativeDateOffsetBuilder.class, getValuationDate());
			processRosetta(path.newSubPath("fxFixingDate"), processor, FxFixingDate.FxFixingDateBuilder.class, getFxFixingDate());
			processRosetta(path.newSubPath("fxFixingSchedule"), processor, AdjustableDates.AdjustableDatesBuilder.class, getFxFixingSchedule());
		}
		

		ValuationDate.ValuationDateBuilder prune();
	}

	/*********************** Immutable Implementation of ValuationDate  ***********************/
	class ValuationDateImpl implements ValuationDate {
		private final SingleValuationDate singleValuationDate;
		private final MultipleValuationDates multipleValuationDates;
		private final RelativeDateOffset valuationDate;
		private final FxFixingDate fxFixingDate;
		private final AdjustableDates fxFixingSchedule;
		
		protected ValuationDateImpl(ValuationDate.ValuationDateBuilder builder) {
			this.singleValuationDate = ofNullable(builder.getSingleValuationDate()).map(f->f.build()).orElse(null);
			this.multipleValuationDates = ofNullable(builder.getMultipleValuationDates()).map(f->f.build()).orElse(null);
			this.valuationDate = ofNullable(builder.getValuationDate()).map(f->f.build()).orElse(null);
			this.fxFixingDate = ofNullable(builder.getFxFixingDate()).map(f->f.build()).orElse(null);
			this.fxFixingSchedule = ofNullable(builder.getFxFixingSchedule()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("singleValuationDate")
		public SingleValuationDate getSingleValuationDate() {
			return singleValuationDate;
		}
		
		@Override
		@RosettaAttribute("multipleValuationDates")
		public MultipleValuationDates getMultipleValuationDates() {
			return multipleValuationDates;
		}
		
		@Override
		@RosettaAttribute("valuationDate")
		public RelativeDateOffset getValuationDate() {
			return valuationDate;
		}
		
		@Override
		@RosettaAttribute("fxFixingDate")
		public FxFixingDate getFxFixingDate() {
			return fxFixingDate;
		}
		
		@Override
		@RosettaAttribute("fxFixingSchedule")
		public AdjustableDates getFxFixingSchedule() {
			return fxFixingSchedule;
		}
		
		@Override
		public ValuationDate build() {
			return this;
		}
		
		@Override
		public ValuationDate.ValuationDateBuilder toBuilder() {
			ValuationDate.ValuationDateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ValuationDate.ValuationDateBuilder builder) {
			ofNullable(getSingleValuationDate()).ifPresent(builder::setSingleValuationDate);
			ofNullable(getMultipleValuationDates()).ifPresent(builder::setMultipleValuationDates);
			ofNullable(getValuationDate()).ifPresent(builder::setValuationDate);
			ofNullable(getFxFixingDate()).ifPresent(builder::setFxFixingDate);
			ofNullable(getFxFixingSchedule()).ifPresent(builder::setFxFixingSchedule);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ValuationDate _that = getType().cast(o);
		
			if (!Objects.equals(singleValuationDate, _that.getSingleValuationDate())) return false;
			if (!Objects.equals(multipleValuationDates, _that.getMultipleValuationDates())) return false;
			if (!Objects.equals(valuationDate, _that.getValuationDate())) return false;
			if (!Objects.equals(fxFixingDate, _that.getFxFixingDate())) return false;
			if (!Objects.equals(fxFixingSchedule, _that.getFxFixingSchedule())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (singleValuationDate != null ? singleValuationDate.hashCode() : 0);
			_result = 31 * _result + (multipleValuationDates != null ? multipleValuationDates.hashCode() : 0);
			_result = 31 * _result + (valuationDate != null ? valuationDate.hashCode() : 0);
			_result = 31 * _result + (fxFixingDate != null ? fxFixingDate.hashCode() : 0);
			_result = 31 * _result + (fxFixingSchedule != null ? fxFixingSchedule.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ValuationDate {" +
				"singleValuationDate=" + this.singleValuationDate + ", " +
				"multipleValuationDates=" + this.multipleValuationDates + ", " +
				"valuationDate=" + this.valuationDate + ", " +
				"fxFixingDate=" + this.fxFixingDate + ", " +
				"fxFixingSchedule=" + this.fxFixingSchedule +
			'}';
		}
	}

	/*********************** Builder Implementation of ValuationDate  ***********************/
	class ValuationDateBuilderImpl implements ValuationDate.ValuationDateBuilder {
	
		protected SingleValuationDate.SingleValuationDateBuilder singleValuationDate;
		protected MultipleValuationDates.MultipleValuationDatesBuilder multipleValuationDates;
		protected RelativeDateOffset.RelativeDateOffsetBuilder valuationDate;
		protected FxFixingDate.FxFixingDateBuilder fxFixingDate;
		protected AdjustableDates.AdjustableDatesBuilder fxFixingSchedule;
	
		public ValuationDateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("singleValuationDate")
		public SingleValuationDate.SingleValuationDateBuilder getSingleValuationDate() {
			return singleValuationDate;
		}
		
		@Override
		public SingleValuationDate.SingleValuationDateBuilder getOrCreateSingleValuationDate() {
			SingleValuationDate.SingleValuationDateBuilder result;
			if (singleValuationDate!=null) {
				result = singleValuationDate;
			}
			else {
				result = singleValuationDate = SingleValuationDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("multipleValuationDates")
		public MultipleValuationDates.MultipleValuationDatesBuilder getMultipleValuationDates() {
			return multipleValuationDates;
		}
		
		@Override
		public MultipleValuationDates.MultipleValuationDatesBuilder getOrCreateMultipleValuationDates() {
			MultipleValuationDates.MultipleValuationDatesBuilder result;
			if (multipleValuationDates!=null) {
				result = multipleValuationDates;
			}
			else {
				result = multipleValuationDates = MultipleValuationDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("valuationDate")
		public RelativeDateOffset.RelativeDateOffsetBuilder getValuationDate() {
			return valuationDate;
		}
		
		@Override
		public RelativeDateOffset.RelativeDateOffsetBuilder getOrCreateValuationDate() {
			RelativeDateOffset.RelativeDateOffsetBuilder result;
			if (valuationDate!=null) {
				result = valuationDate;
			}
			else {
				result = valuationDate = RelativeDateOffset.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("fxFixingDate")
		public FxFixingDate.FxFixingDateBuilder getFxFixingDate() {
			return fxFixingDate;
		}
		
		@Override
		public FxFixingDate.FxFixingDateBuilder getOrCreateFxFixingDate() {
			FxFixingDate.FxFixingDateBuilder result;
			if (fxFixingDate!=null) {
				result = fxFixingDate;
			}
			else {
				result = fxFixingDate = FxFixingDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("fxFixingSchedule")
		public AdjustableDates.AdjustableDatesBuilder getFxFixingSchedule() {
			return fxFixingSchedule;
		}
		
		@Override
		public AdjustableDates.AdjustableDatesBuilder getOrCreateFxFixingSchedule() {
			AdjustableDates.AdjustableDatesBuilder result;
			if (fxFixingSchedule!=null) {
				result = fxFixingSchedule;
			}
			else {
				result = fxFixingSchedule = AdjustableDates.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("singleValuationDate")
		public ValuationDate.ValuationDateBuilder setSingleValuationDate(SingleValuationDate singleValuationDate) {
			this.singleValuationDate = singleValuationDate==null?null:singleValuationDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("multipleValuationDates")
		public ValuationDate.ValuationDateBuilder setMultipleValuationDates(MultipleValuationDates multipleValuationDates) {
			this.multipleValuationDates = multipleValuationDates==null?null:multipleValuationDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("valuationDate")
		public ValuationDate.ValuationDateBuilder setValuationDate(RelativeDateOffset valuationDate) {
			this.valuationDate = valuationDate==null?null:valuationDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("fxFixingDate")
		public ValuationDate.ValuationDateBuilder setFxFixingDate(FxFixingDate fxFixingDate) {
			this.fxFixingDate = fxFixingDate==null?null:fxFixingDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("fxFixingSchedule")
		public ValuationDate.ValuationDateBuilder setFxFixingSchedule(AdjustableDates fxFixingSchedule) {
			this.fxFixingSchedule = fxFixingSchedule==null?null:fxFixingSchedule.toBuilder();
			return this;
		}
		
		@Override
		public ValuationDate build() {
			return new ValuationDate.ValuationDateImpl(this);
		}
		
		@Override
		public ValuationDate.ValuationDateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ValuationDate.ValuationDateBuilder prune() {
			if (singleValuationDate!=null && !singleValuationDate.prune().hasData()) singleValuationDate = null;
			if (multipleValuationDates!=null && !multipleValuationDates.prune().hasData()) multipleValuationDates = null;
			if (valuationDate!=null && !valuationDate.prune().hasData()) valuationDate = null;
			if (fxFixingDate!=null && !fxFixingDate.prune().hasData()) fxFixingDate = null;
			if (fxFixingSchedule!=null && !fxFixingSchedule.prune().hasData()) fxFixingSchedule = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSingleValuationDate()!=null && getSingleValuationDate().hasData()) return true;
			if (getMultipleValuationDates()!=null && getMultipleValuationDates().hasData()) return true;
			if (getValuationDate()!=null && getValuationDate().hasData()) return true;
			if (getFxFixingDate()!=null && getFxFixingDate().hasData()) return true;
			if (getFxFixingSchedule()!=null && getFxFixingSchedule().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ValuationDate.ValuationDateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ValuationDate.ValuationDateBuilder o = (ValuationDate.ValuationDateBuilder) other;
			
			merger.mergeRosetta(getSingleValuationDate(), o.getSingleValuationDate(), this::setSingleValuationDate);
			merger.mergeRosetta(getMultipleValuationDates(), o.getMultipleValuationDates(), this::setMultipleValuationDates);
			merger.mergeRosetta(getValuationDate(), o.getValuationDate(), this::setValuationDate);
			merger.mergeRosetta(getFxFixingDate(), o.getFxFixingDate(), this::setFxFixingDate);
			merger.mergeRosetta(getFxFixingSchedule(), o.getFxFixingSchedule(), this::setFxFixingSchedule);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ValuationDate _that = getType().cast(o);
		
			if (!Objects.equals(singleValuationDate, _that.getSingleValuationDate())) return false;
			if (!Objects.equals(multipleValuationDates, _that.getMultipleValuationDates())) return false;
			if (!Objects.equals(valuationDate, _that.getValuationDate())) return false;
			if (!Objects.equals(fxFixingDate, _that.getFxFixingDate())) return false;
			if (!Objects.equals(fxFixingSchedule, _that.getFxFixingSchedule())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (singleValuationDate != null ? singleValuationDate.hashCode() : 0);
			_result = 31 * _result + (multipleValuationDates != null ? multipleValuationDates.hashCode() : 0);
			_result = 31 * _result + (valuationDate != null ? valuationDate.hashCode() : 0);
			_result = 31 * _result + (fxFixingDate != null ? fxFixingDate.hashCode() : 0);
			_result = 31 * _result + (fxFixingSchedule != null ? fxFixingSchedule.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ValuationDateBuilder {" +
				"singleValuationDate=" + this.singleValuationDate + ", " +
				"multipleValuationDates=" + this.multipleValuationDates + ", " +
				"valuationDate=" + this.valuationDate + ", " +
				"fxFixingDate=" + this.fxFixingDate + ", " +
				"fxFixingSchedule=" + this.fxFixingSchedule +
			'}';
		}
	}
}
