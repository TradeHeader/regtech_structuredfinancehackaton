package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.CreditRiskEnum;
import cdm.base.staticdata.asset.common.SpecialPurposeVehicleIssuerType;
import cdm.base.staticdata.asset.common.SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder;
import cdm.base.staticdata.asset.common.SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilderImpl;
import cdm.base.staticdata.asset.common.SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeImpl;
import cdm.base.staticdata.asset.common.meta.SpecialPurposeVehicleIssuerTypeMeta;
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
 * Represents a class to allow specification of different types of special purpose vehicle (SPV) collateral.
 * @version ${project.version}
 */
@RosettaDataType(value="SpecialPurposeVehicleIssuerType", builder=SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilderImpl.class, version="${project.version}")
public interface SpecialPurposeVehicleIssuerType extends RosettaModelObject {

	SpecialPurposeVehicleIssuerTypeMeta metaData = new SpecialPurposeVehicleIssuerTypeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Indicates tranched or untranched credit risk.
	 */
	CreditRiskEnum getCreditRisk();

	/*********************** Build Methods  ***********************/
	SpecialPurposeVehicleIssuerType build();
	
	SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder toBuilder();
	
	static SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder builder() {
		return new SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SpecialPurposeVehicleIssuerType> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SpecialPurposeVehicleIssuerType> getType() {
		return SpecialPurposeVehicleIssuerType.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("creditRisk"), CreditRiskEnum.class, getCreditRisk(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface SpecialPurposeVehicleIssuerTypeBuilder extends SpecialPurposeVehicleIssuerType, RosettaModelObjectBuilder {
		SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder setCreditRisk(CreditRiskEnum creditRisk);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("creditRisk"), CreditRiskEnum.class, getCreditRisk(), this);
		}
		

		SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder prune();
	}

	/*********************** Immutable Implementation of SpecialPurposeVehicleIssuerType  ***********************/
	class SpecialPurposeVehicleIssuerTypeImpl implements SpecialPurposeVehicleIssuerType {
		private final CreditRiskEnum creditRisk;
		
		protected SpecialPurposeVehicleIssuerTypeImpl(SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder builder) {
			this.creditRisk = builder.getCreditRisk();
		}
		
		@Override
		@RosettaAttribute("creditRisk")
		public CreditRiskEnum getCreditRisk() {
			return creditRisk;
		}
		
		@Override
		public SpecialPurposeVehicleIssuerType build() {
			return this;
		}
		
		@Override
		public SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder toBuilder() {
			SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder builder) {
			ofNullable(getCreditRisk()).ifPresent(builder::setCreditRisk);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SpecialPurposeVehicleIssuerType _that = getType().cast(o);
		
			if (!Objects.equals(creditRisk, _that.getCreditRisk())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (creditRisk != null ? creditRisk.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SpecialPurposeVehicleIssuerType {" +
				"creditRisk=" + this.creditRisk +
			'}';
		}
	}

	/*********************** Builder Implementation of SpecialPurposeVehicleIssuerType  ***********************/
	class SpecialPurposeVehicleIssuerTypeBuilderImpl implements SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder {
	
		protected CreditRiskEnum creditRisk;
	
		public SpecialPurposeVehicleIssuerTypeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("creditRisk")
		public CreditRiskEnum getCreditRisk() {
			return creditRisk;
		}
		
	
		@Override
		@RosettaAttribute("creditRisk")
		public SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder setCreditRisk(CreditRiskEnum creditRisk) {
			this.creditRisk = creditRisk==null?null:creditRisk;
			return this;
		}
		
		@Override
		public SpecialPurposeVehicleIssuerType build() {
			return new SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeImpl(this);
		}
		
		@Override
		public SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCreditRisk()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder o = (SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder) other;
			
			
			merger.mergeBasic(getCreditRisk(), o.getCreditRisk(), this::setCreditRisk);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SpecialPurposeVehicleIssuerType _that = getType().cast(o);
		
			if (!Objects.equals(creditRisk, _that.getCreditRisk())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (creditRisk != null ? creditRisk.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SpecialPurposeVehicleIssuerTypeBuilder {" +
				"creditRisk=" + this.creditRisk +
			'}';
		}
	}
}
