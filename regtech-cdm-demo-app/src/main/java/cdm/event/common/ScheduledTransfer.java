package cdm.event.common;

import cdm.event.common.CorporateActionTypeEnum;
import cdm.event.common.ScheduledTransfer;
import cdm.event.common.ScheduledTransfer.ScheduledTransferBuilder;
import cdm.event.common.ScheduledTransfer.ScheduledTransferBuilderImpl;
import cdm.event.common.ScheduledTransfer.ScheduledTransferImpl;
import cdm.event.common.meta.ScheduledTransferMeta;
import cdm.product.common.settlement.ScheduledTransferEnum;
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
 * @version ${project.version}
 */
@RosettaDataType(value="ScheduledTransfer", builder=ScheduledTransfer.ScheduledTransferBuilderImpl.class, version="${project.version}")
public interface ScheduledTransfer extends RosettaModelObject {

	ScheduledTransferMeta metaData = new ScheduledTransferMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies a transfer created from a scheduled or contingent event on a contract, e.g. Exercise, Performance, Credit Event
	 */
	ScheduledTransferEnum getTransferType();
	CorporateActionTypeEnum getCorporateActionTransferType();

	/*********************** Build Methods  ***********************/
	ScheduledTransfer build();
	
	ScheduledTransfer.ScheduledTransferBuilder toBuilder();
	
	static ScheduledTransfer.ScheduledTransferBuilder builder() {
		return new ScheduledTransfer.ScheduledTransferBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ScheduledTransfer> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ScheduledTransfer> getType() {
		return ScheduledTransfer.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("transferType"), ScheduledTransferEnum.class, getTransferType(), this);
		processor.processBasic(path.newSubPath("corporateActionTransferType"), CorporateActionTypeEnum.class, getCorporateActionTransferType(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ScheduledTransferBuilder extends ScheduledTransfer, RosettaModelObjectBuilder {
		ScheduledTransfer.ScheduledTransferBuilder setTransferType(ScheduledTransferEnum transferType);
		ScheduledTransfer.ScheduledTransferBuilder setCorporateActionTransferType(CorporateActionTypeEnum corporateActionTransferType);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("transferType"), ScheduledTransferEnum.class, getTransferType(), this);
			processor.processBasic(path.newSubPath("corporateActionTransferType"), CorporateActionTypeEnum.class, getCorporateActionTransferType(), this);
		}
		

		ScheduledTransfer.ScheduledTransferBuilder prune();
	}

	/*********************** Immutable Implementation of ScheduledTransfer  ***********************/
	class ScheduledTransferImpl implements ScheduledTransfer {
		private final ScheduledTransferEnum transferType;
		private final CorporateActionTypeEnum corporateActionTransferType;
		
		protected ScheduledTransferImpl(ScheduledTransfer.ScheduledTransferBuilder builder) {
			this.transferType = builder.getTransferType();
			this.corporateActionTransferType = builder.getCorporateActionTransferType();
		}
		
		@Override
		@RosettaAttribute("transferType")
		public ScheduledTransferEnum getTransferType() {
			return transferType;
		}
		
		@Override
		@RosettaAttribute("corporateActionTransferType")
		public CorporateActionTypeEnum getCorporateActionTransferType() {
			return corporateActionTransferType;
		}
		
		@Override
		public ScheduledTransfer build() {
			return this;
		}
		
		@Override
		public ScheduledTransfer.ScheduledTransferBuilder toBuilder() {
			ScheduledTransfer.ScheduledTransferBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ScheduledTransfer.ScheduledTransferBuilder builder) {
			ofNullable(getTransferType()).ifPresent(builder::setTransferType);
			ofNullable(getCorporateActionTransferType()).ifPresent(builder::setCorporateActionTransferType);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ScheduledTransfer _that = getType().cast(o);
		
			if (!Objects.equals(transferType, _that.getTransferType())) return false;
			if (!Objects.equals(corporateActionTransferType, _that.getCorporateActionTransferType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (transferType != null ? transferType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (corporateActionTransferType != null ? corporateActionTransferType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ScheduledTransfer {" +
				"transferType=" + this.transferType + ", " +
				"corporateActionTransferType=" + this.corporateActionTransferType +
			'}';
		}
	}

	/*********************** Builder Implementation of ScheduledTransfer  ***********************/
	class ScheduledTransferBuilderImpl implements ScheduledTransfer.ScheduledTransferBuilder {
	
		protected ScheduledTransferEnum transferType;
		protected CorporateActionTypeEnum corporateActionTransferType;
	
		public ScheduledTransferBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("transferType")
		public ScheduledTransferEnum getTransferType() {
			return transferType;
		}
		
		@Override
		@RosettaAttribute("corporateActionTransferType")
		public CorporateActionTypeEnum getCorporateActionTransferType() {
			return corporateActionTransferType;
		}
		
	
		@Override
		@RosettaAttribute("transferType")
		public ScheduledTransfer.ScheduledTransferBuilder setTransferType(ScheduledTransferEnum transferType) {
			this.transferType = transferType==null?null:transferType;
			return this;
		}
		@Override
		@RosettaAttribute("corporateActionTransferType")
		public ScheduledTransfer.ScheduledTransferBuilder setCorporateActionTransferType(CorporateActionTypeEnum corporateActionTransferType) {
			this.corporateActionTransferType = corporateActionTransferType==null?null:corporateActionTransferType;
			return this;
		}
		
		@Override
		public ScheduledTransfer build() {
			return new ScheduledTransfer.ScheduledTransferImpl(this);
		}
		
		@Override
		public ScheduledTransfer.ScheduledTransferBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ScheduledTransfer.ScheduledTransferBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getTransferType()!=null) return true;
			if (getCorporateActionTransferType()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ScheduledTransfer.ScheduledTransferBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ScheduledTransfer.ScheduledTransferBuilder o = (ScheduledTransfer.ScheduledTransferBuilder) other;
			
			
			merger.mergeBasic(getTransferType(), o.getTransferType(), this::setTransferType);
			merger.mergeBasic(getCorporateActionTransferType(), o.getCorporateActionTransferType(), this::setCorporateActionTransferType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ScheduledTransfer _that = getType().cast(o);
		
			if (!Objects.equals(transferType, _that.getTransferType())) return false;
			if (!Objects.equals(corporateActionTransferType, _that.getCorporateActionTransferType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (transferType != null ? transferType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (corporateActionTransferType != null ? corporateActionTransferType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ScheduledTransferBuilder {" +
				"transferType=" + this.transferType + ", " +
				"corporateActionTransferType=" + this.corporateActionTransferType +
			'}';
		}
	}
}
