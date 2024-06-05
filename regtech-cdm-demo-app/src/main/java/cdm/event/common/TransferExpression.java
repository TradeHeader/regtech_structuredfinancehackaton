package cdm.event.common;

import cdm.event.common.ScheduledTransfer;
import cdm.event.common.TransferExpression;
import cdm.event.common.TransferExpression.TransferExpressionBuilder;
import cdm.event.common.TransferExpression.TransferExpressionBuilderImpl;
import cdm.event.common.TransferExpression.TransferExpressionImpl;
import cdm.event.common.meta.TransferExpressionMeta;
import cdm.observable.asset.FeeTypeEnum;
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
 * Specifies a transfer expression (cash price, performance amount, scheduled payment amount, etc.) to define the nature of the transfer amount and its source.
 * @version ${project.version}
 */
@RosettaDataType(value="TransferExpression", builder=TransferExpression.TransferExpressionBuilderImpl.class, version="${project.version}")
public interface TransferExpression extends RosettaModelObject {

	TransferExpressionMeta metaData = new TransferExpressionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies a transfer amount exchanged as a price or fee for entering into a Business Event, e.g. Premium, Termination fee, Novation fee.
	 */
	FeeTypeEnum getPriceTransfer();
	/**
	 * Specifies a transfer created from a scheduled or contingent event on a contract, e.g. Exercise, Performance, Credit Event
	 */
	ScheduledTransfer getScheduledTransfer();

	/*********************** Build Methods  ***********************/
	TransferExpression build();
	
	TransferExpression.TransferExpressionBuilder toBuilder();
	
	static TransferExpression.TransferExpressionBuilder builder() {
		return new TransferExpression.TransferExpressionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends TransferExpression> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends TransferExpression> getType() {
		return TransferExpression.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("priceTransfer"), FeeTypeEnum.class, getPriceTransfer(), this);
		processRosetta(path.newSubPath("scheduledTransfer"), processor, ScheduledTransfer.class, getScheduledTransfer());
	}
	

	/*********************** Builder Interface  ***********************/
	interface TransferExpressionBuilder extends TransferExpression, RosettaModelObjectBuilder {
		ScheduledTransfer.ScheduledTransferBuilder getOrCreateScheduledTransfer();
		ScheduledTransfer.ScheduledTransferBuilder getScheduledTransfer();
		TransferExpression.TransferExpressionBuilder setPriceTransfer(FeeTypeEnum priceTransfer);
		TransferExpression.TransferExpressionBuilder setScheduledTransfer(ScheduledTransfer scheduledTransfer);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("priceTransfer"), FeeTypeEnum.class, getPriceTransfer(), this);
			processRosetta(path.newSubPath("scheduledTransfer"), processor, ScheduledTransfer.ScheduledTransferBuilder.class, getScheduledTransfer());
		}
		

		TransferExpression.TransferExpressionBuilder prune();
	}

	/*********************** Immutable Implementation of TransferExpression  ***********************/
	class TransferExpressionImpl implements TransferExpression {
		private final FeeTypeEnum priceTransfer;
		private final ScheduledTransfer scheduledTransfer;
		
		protected TransferExpressionImpl(TransferExpression.TransferExpressionBuilder builder) {
			this.priceTransfer = builder.getPriceTransfer();
			this.scheduledTransfer = ofNullable(builder.getScheduledTransfer()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("priceTransfer")
		public FeeTypeEnum getPriceTransfer() {
			return priceTransfer;
		}
		
		@Override
		@RosettaAttribute("scheduledTransfer")
		public ScheduledTransfer getScheduledTransfer() {
			return scheduledTransfer;
		}
		
		@Override
		public TransferExpression build() {
			return this;
		}
		
		@Override
		public TransferExpression.TransferExpressionBuilder toBuilder() {
			TransferExpression.TransferExpressionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(TransferExpression.TransferExpressionBuilder builder) {
			ofNullable(getPriceTransfer()).ifPresent(builder::setPriceTransfer);
			ofNullable(getScheduledTransfer()).ifPresent(builder::setScheduledTransfer);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TransferExpression _that = getType().cast(o);
		
			if (!Objects.equals(priceTransfer, _that.getPriceTransfer())) return false;
			if (!Objects.equals(scheduledTransfer, _that.getScheduledTransfer())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (priceTransfer != null ? priceTransfer.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (scheduledTransfer != null ? scheduledTransfer.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TransferExpression {" +
				"priceTransfer=" + this.priceTransfer + ", " +
				"scheduledTransfer=" + this.scheduledTransfer +
			'}';
		}
	}

	/*********************** Builder Implementation of TransferExpression  ***********************/
	class TransferExpressionBuilderImpl implements TransferExpression.TransferExpressionBuilder {
	
		protected FeeTypeEnum priceTransfer;
		protected ScheduledTransfer.ScheduledTransferBuilder scheduledTransfer;
	
		public TransferExpressionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("priceTransfer")
		public FeeTypeEnum getPriceTransfer() {
			return priceTransfer;
		}
		
		@Override
		@RosettaAttribute("scheduledTransfer")
		public ScheduledTransfer.ScheduledTransferBuilder getScheduledTransfer() {
			return scheduledTransfer;
		}
		
		@Override
		public ScheduledTransfer.ScheduledTransferBuilder getOrCreateScheduledTransfer() {
			ScheduledTransfer.ScheduledTransferBuilder result;
			if (scheduledTransfer!=null) {
				result = scheduledTransfer;
			}
			else {
				result = scheduledTransfer = ScheduledTransfer.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("priceTransfer")
		public TransferExpression.TransferExpressionBuilder setPriceTransfer(FeeTypeEnum priceTransfer) {
			this.priceTransfer = priceTransfer==null?null:priceTransfer;
			return this;
		}
		@Override
		@RosettaAttribute("scheduledTransfer")
		public TransferExpression.TransferExpressionBuilder setScheduledTransfer(ScheduledTransfer scheduledTransfer) {
			this.scheduledTransfer = scheduledTransfer==null?null:scheduledTransfer.toBuilder();
			return this;
		}
		
		@Override
		public TransferExpression build() {
			return new TransferExpression.TransferExpressionImpl(this);
		}
		
		@Override
		public TransferExpression.TransferExpressionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TransferExpression.TransferExpressionBuilder prune() {
			if (scheduledTransfer!=null && !scheduledTransfer.prune().hasData()) scheduledTransfer = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPriceTransfer()!=null) return true;
			if (getScheduledTransfer()!=null && getScheduledTransfer().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TransferExpression.TransferExpressionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			TransferExpression.TransferExpressionBuilder o = (TransferExpression.TransferExpressionBuilder) other;
			
			merger.mergeRosetta(getScheduledTransfer(), o.getScheduledTransfer(), this::setScheduledTransfer);
			
			merger.mergeBasic(getPriceTransfer(), o.getPriceTransfer(), this::setPriceTransfer);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TransferExpression _that = getType().cast(o);
		
			if (!Objects.equals(priceTransfer, _that.getPriceTransfer())) return false;
			if (!Objects.equals(scheduledTransfer, _that.getScheduledTransfer())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (priceTransfer != null ? priceTransfer.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (scheduledTransfer != null ? scheduledTransfer.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TransferExpressionBuilder {" +
				"priceTransfer=" + this.priceTransfer + ", " +
				"scheduledTransfer=" + this.scheduledTransfer +
			'}';
		}
	}
}
