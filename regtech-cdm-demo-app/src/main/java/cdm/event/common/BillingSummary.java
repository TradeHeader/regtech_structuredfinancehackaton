package cdm.event.common;

import cdm.event.common.BillingSummary;
import cdm.event.common.BillingSummary.BillingSummaryBuilder;
import cdm.event.common.BillingSummary.BillingSummaryBuilderImpl;
import cdm.event.common.BillingSummary.BillingSummaryImpl;
import cdm.event.common.RecordAmountTypeEnum;
import cdm.event.common.Transfer;
import cdm.event.common.meta.BillingSummaryMeta;
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
 * Specifies individual summaries within a billing invoice.
 * @version ${project.version}
 */
@RosettaDataType(value="BillingSummary", builder=BillingSummary.BillingSummaryBuilderImpl.class, version="${project.version}")
public interface BillingSummary extends RosettaModelObject {

	BillingSummaryMeta metaData = new BillingSummaryMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The settlement terms for the billing summary
	 */
	Transfer getSummaryTransfer();
	/**
	 * The account level for the billing summary.
	 */
	RecordAmountTypeEnum getSummaryAmountType();

	/*********************** Build Methods  ***********************/
	BillingSummary build();
	
	BillingSummary.BillingSummaryBuilder toBuilder();
	
	static BillingSummary.BillingSummaryBuilder builder() {
		return new BillingSummary.BillingSummaryBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends BillingSummary> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends BillingSummary> getType() {
		return BillingSummary.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("summaryTransfer"), processor, Transfer.class, getSummaryTransfer());
		processor.processBasic(path.newSubPath("summaryAmountType"), RecordAmountTypeEnum.class, getSummaryAmountType(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface BillingSummaryBuilder extends BillingSummary, RosettaModelObjectBuilder {
		Transfer.TransferBuilder getOrCreateSummaryTransfer();
		Transfer.TransferBuilder getSummaryTransfer();
		BillingSummary.BillingSummaryBuilder setSummaryTransfer(Transfer summaryTransfer);
		BillingSummary.BillingSummaryBuilder setSummaryAmountType(RecordAmountTypeEnum summaryAmountType);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("summaryTransfer"), processor, Transfer.TransferBuilder.class, getSummaryTransfer());
			processor.processBasic(path.newSubPath("summaryAmountType"), RecordAmountTypeEnum.class, getSummaryAmountType(), this);
		}
		

		BillingSummary.BillingSummaryBuilder prune();
	}

	/*********************** Immutable Implementation of BillingSummary  ***********************/
	class BillingSummaryImpl implements BillingSummary {
		private final Transfer summaryTransfer;
		private final RecordAmountTypeEnum summaryAmountType;
		
		protected BillingSummaryImpl(BillingSummary.BillingSummaryBuilder builder) {
			this.summaryTransfer = ofNullable(builder.getSummaryTransfer()).map(f->f.build()).orElse(null);
			this.summaryAmountType = builder.getSummaryAmountType();
		}
		
		@Override
		@RosettaAttribute("summaryTransfer")
		public Transfer getSummaryTransfer() {
			return summaryTransfer;
		}
		
		@Override
		@RosettaAttribute("summaryAmountType")
		public RecordAmountTypeEnum getSummaryAmountType() {
			return summaryAmountType;
		}
		
		@Override
		public BillingSummary build() {
			return this;
		}
		
		@Override
		public BillingSummary.BillingSummaryBuilder toBuilder() {
			BillingSummary.BillingSummaryBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(BillingSummary.BillingSummaryBuilder builder) {
			ofNullable(getSummaryTransfer()).ifPresent(builder::setSummaryTransfer);
			ofNullable(getSummaryAmountType()).ifPresent(builder::setSummaryAmountType);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BillingSummary _that = getType().cast(o);
		
			if (!Objects.equals(summaryTransfer, _that.getSummaryTransfer())) return false;
			if (!Objects.equals(summaryAmountType, _that.getSummaryAmountType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (summaryTransfer != null ? summaryTransfer.hashCode() : 0);
			_result = 31 * _result + (summaryAmountType != null ? summaryAmountType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BillingSummary {" +
				"summaryTransfer=" + this.summaryTransfer + ", " +
				"summaryAmountType=" + this.summaryAmountType +
			'}';
		}
	}

	/*********************** Builder Implementation of BillingSummary  ***********************/
	class BillingSummaryBuilderImpl implements BillingSummary.BillingSummaryBuilder {
	
		protected Transfer.TransferBuilder summaryTransfer;
		protected RecordAmountTypeEnum summaryAmountType;
	
		public BillingSummaryBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("summaryTransfer")
		public Transfer.TransferBuilder getSummaryTransfer() {
			return summaryTransfer;
		}
		
		@Override
		public Transfer.TransferBuilder getOrCreateSummaryTransfer() {
			Transfer.TransferBuilder result;
			if (summaryTransfer!=null) {
				result = summaryTransfer;
			}
			else {
				result = summaryTransfer = Transfer.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("summaryAmountType")
		public RecordAmountTypeEnum getSummaryAmountType() {
			return summaryAmountType;
		}
		
	
		@Override
		@RosettaAttribute("summaryTransfer")
		public BillingSummary.BillingSummaryBuilder setSummaryTransfer(Transfer summaryTransfer) {
			this.summaryTransfer = summaryTransfer==null?null:summaryTransfer.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("summaryAmountType")
		public BillingSummary.BillingSummaryBuilder setSummaryAmountType(RecordAmountTypeEnum summaryAmountType) {
			this.summaryAmountType = summaryAmountType==null?null:summaryAmountType;
			return this;
		}
		
		@Override
		public BillingSummary build() {
			return new BillingSummary.BillingSummaryImpl(this);
		}
		
		@Override
		public BillingSummary.BillingSummaryBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BillingSummary.BillingSummaryBuilder prune() {
			if (summaryTransfer!=null && !summaryTransfer.prune().hasData()) summaryTransfer = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSummaryTransfer()!=null && getSummaryTransfer().hasData()) return true;
			if (getSummaryAmountType()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BillingSummary.BillingSummaryBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			BillingSummary.BillingSummaryBuilder o = (BillingSummary.BillingSummaryBuilder) other;
			
			merger.mergeRosetta(getSummaryTransfer(), o.getSummaryTransfer(), this::setSummaryTransfer);
			
			merger.mergeBasic(getSummaryAmountType(), o.getSummaryAmountType(), this::setSummaryAmountType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BillingSummary _that = getType().cast(o);
		
			if (!Objects.equals(summaryTransfer, _that.getSummaryTransfer())) return false;
			if (!Objects.equals(summaryAmountType, _that.getSummaryAmountType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (summaryTransfer != null ? summaryTransfer.hashCode() : 0);
			_result = 31 * _result + (summaryAmountType != null ? summaryAmountType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BillingSummaryBuilder {" +
				"summaryTransfer=" + this.summaryTransfer + ", " +
				"summaryAmountType=" + this.summaryAmountType +
			'}';
		}
	}
}
