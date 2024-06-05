package cdm.event.position;

import cdm.event.common.ContractDetails;
import cdm.event.common.ExecutionDetails;
import cdm.event.common.metafields.ReferenceWithMetaContractDetails;
import cdm.event.common.metafields.ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder;
import cdm.event.common.metafields.ReferenceWithMetaExecutionDetails;
import cdm.event.common.metafields.ReferenceWithMetaExecutionDetails.ReferenceWithMetaExecutionDetailsBuilder;
import cdm.event.position.ContractBase;
import cdm.event.position.ContractBase.ContractBaseBuilder;
import cdm.event.position.ContractBase.ContractBaseBuilderImpl;
import cdm.event.position.ContractBase.ContractBaseImpl;
import cdm.event.position.meta.ContractBaseMeta;
import cdm.product.collateral.Collateral;
import cdm.product.collateral.metafields.ReferenceWithMetaCollateral;
import cdm.product.collateral.metafields.ReferenceWithMetaCollateral.ReferenceWithMetaCollateralBuilder;
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
 * Encapsulates data features common to trade and position.
 * @version ${project.version}
 */
@RosettaDataType(value="ContractBase", builder=ContractBase.ContractBaseBuilderImpl.class, version="${project.version}")
public interface ContractBase extends RosettaModelObject {

	ContractBaseMeta metaData = new ContractBaseMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents information specific to trades or positions involving contractual products.
	 */
	ReferenceWithMetaContractDetails getContractDetails();
	/**
	 * Defines specific attributes that relate to trade or position executions.
	 */
	ReferenceWithMetaExecutionDetails getExecutionDetails();
	/**
	 * Represents the collateral obligations of a party.
	 */
	ReferenceWithMetaCollateral getCollateral();

	/*********************** Build Methods  ***********************/
	ContractBase build();
	
	ContractBase.ContractBaseBuilder toBuilder();
	
	static ContractBase.ContractBaseBuilder builder() {
		return new ContractBase.ContractBaseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ContractBase> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ContractBase> getType() {
		return ContractBase.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("contractDetails"), processor, ReferenceWithMetaContractDetails.class, getContractDetails());
		processRosetta(path.newSubPath("executionDetails"), processor, ReferenceWithMetaExecutionDetails.class, getExecutionDetails());
		processRosetta(path.newSubPath("collateral"), processor, ReferenceWithMetaCollateral.class, getCollateral());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ContractBaseBuilder extends ContractBase, RosettaModelObjectBuilder {
		ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder getOrCreateContractDetails();
		ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder getContractDetails();
		ReferenceWithMetaExecutionDetails.ReferenceWithMetaExecutionDetailsBuilder getOrCreateExecutionDetails();
		ReferenceWithMetaExecutionDetails.ReferenceWithMetaExecutionDetailsBuilder getExecutionDetails();
		ReferenceWithMetaCollateral.ReferenceWithMetaCollateralBuilder getOrCreateCollateral();
		ReferenceWithMetaCollateral.ReferenceWithMetaCollateralBuilder getCollateral();
		ContractBase.ContractBaseBuilder setContractDetails(ReferenceWithMetaContractDetails contractDetails0);
		ContractBase.ContractBaseBuilder setContractDetailsValue(ContractDetails contractDetails1);
		ContractBase.ContractBaseBuilder setExecutionDetails(ReferenceWithMetaExecutionDetails executionDetails0);
		ContractBase.ContractBaseBuilder setExecutionDetailsValue(ExecutionDetails executionDetails1);
		ContractBase.ContractBaseBuilder setCollateral(ReferenceWithMetaCollateral collateral0);
		ContractBase.ContractBaseBuilder setCollateralValue(Collateral collateral1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("contractDetails"), processor, ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder.class, getContractDetails());
			processRosetta(path.newSubPath("executionDetails"), processor, ReferenceWithMetaExecutionDetails.ReferenceWithMetaExecutionDetailsBuilder.class, getExecutionDetails());
			processRosetta(path.newSubPath("collateral"), processor, ReferenceWithMetaCollateral.ReferenceWithMetaCollateralBuilder.class, getCollateral());
		}
		

		ContractBase.ContractBaseBuilder prune();
	}

	/*********************** Immutable Implementation of ContractBase  ***********************/
	class ContractBaseImpl implements ContractBase {
		private final ReferenceWithMetaContractDetails contractDetails;
		private final ReferenceWithMetaExecutionDetails executionDetails;
		private final ReferenceWithMetaCollateral collateral;
		
		protected ContractBaseImpl(ContractBase.ContractBaseBuilder builder) {
			this.contractDetails = ofNullable(builder.getContractDetails()).map(f->f.build()).orElse(null);
			this.executionDetails = ofNullable(builder.getExecutionDetails()).map(f->f.build()).orElse(null);
			this.collateral = ofNullable(builder.getCollateral()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("contractDetails")
		public ReferenceWithMetaContractDetails getContractDetails() {
			return contractDetails;
		}
		
		@Override
		@RosettaAttribute("executionDetails")
		public ReferenceWithMetaExecutionDetails getExecutionDetails() {
			return executionDetails;
		}
		
		@Override
		@RosettaAttribute("collateral")
		public ReferenceWithMetaCollateral getCollateral() {
			return collateral;
		}
		
		@Override
		public ContractBase build() {
			return this;
		}
		
		@Override
		public ContractBase.ContractBaseBuilder toBuilder() {
			ContractBase.ContractBaseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ContractBase.ContractBaseBuilder builder) {
			ofNullable(getContractDetails()).ifPresent(builder::setContractDetails);
			ofNullable(getExecutionDetails()).ifPresent(builder::setExecutionDetails);
			ofNullable(getCollateral()).ifPresent(builder::setCollateral);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ContractBase _that = getType().cast(o);
		
			if (!Objects.equals(contractDetails, _that.getContractDetails())) return false;
			if (!Objects.equals(executionDetails, _that.getExecutionDetails())) return false;
			if (!Objects.equals(collateral, _that.getCollateral())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (contractDetails != null ? contractDetails.hashCode() : 0);
			_result = 31 * _result + (executionDetails != null ? executionDetails.hashCode() : 0);
			_result = 31 * _result + (collateral != null ? collateral.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ContractBase {" +
				"contractDetails=" + this.contractDetails + ", " +
				"executionDetails=" + this.executionDetails + ", " +
				"collateral=" + this.collateral +
			'}';
		}
	}

	/*********************** Builder Implementation of ContractBase  ***********************/
	class ContractBaseBuilderImpl implements ContractBase.ContractBaseBuilder {
	
		protected ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder contractDetails;
		protected ReferenceWithMetaExecutionDetails.ReferenceWithMetaExecutionDetailsBuilder executionDetails;
		protected ReferenceWithMetaCollateral.ReferenceWithMetaCollateralBuilder collateral;
	
		public ContractBaseBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("contractDetails")
		public ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder getContractDetails() {
			return contractDetails;
		}
		
		@Override
		public ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder getOrCreateContractDetails() {
			ReferenceWithMetaContractDetails.ReferenceWithMetaContractDetailsBuilder result;
			if (contractDetails!=null) {
				result = contractDetails;
			}
			else {
				result = contractDetails = ReferenceWithMetaContractDetails.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("executionDetails")
		public ReferenceWithMetaExecutionDetails.ReferenceWithMetaExecutionDetailsBuilder getExecutionDetails() {
			return executionDetails;
		}
		
		@Override
		public ReferenceWithMetaExecutionDetails.ReferenceWithMetaExecutionDetailsBuilder getOrCreateExecutionDetails() {
			ReferenceWithMetaExecutionDetails.ReferenceWithMetaExecutionDetailsBuilder result;
			if (executionDetails!=null) {
				result = executionDetails;
			}
			else {
				result = executionDetails = ReferenceWithMetaExecutionDetails.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("collateral")
		public ReferenceWithMetaCollateral.ReferenceWithMetaCollateralBuilder getCollateral() {
			return collateral;
		}
		
		@Override
		public ReferenceWithMetaCollateral.ReferenceWithMetaCollateralBuilder getOrCreateCollateral() {
			ReferenceWithMetaCollateral.ReferenceWithMetaCollateralBuilder result;
			if (collateral!=null) {
				result = collateral;
			}
			else {
				result = collateral = ReferenceWithMetaCollateral.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("contractDetails")
		public ContractBase.ContractBaseBuilder setContractDetails(ReferenceWithMetaContractDetails contractDetails) {
			this.contractDetails = contractDetails==null?null:contractDetails.toBuilder();
			return this;
		}
		@Override
		public ContractBase.ContractBaseBuilder setContractDetailsValue(ContractDetails contractDetails) {
			this.getOrCreateContractDetails().setValue(contractDetails);
			return this;
		}
		@Override
		@RosettaAttribute("executionDetails")
		public ContractBase.ContractBaseBuilder setExecutionDetails(ReferenceWithMetaExecutionDetails executionDetails) {
			this.executionDetails = executionDetails==null?null:executionDetails.toBuilder();
			return this;
		}
		@Override
		public ContractBase.ContractBaseBuilder setExecutionDetailsValue(ExecutionDetails executionDetails) {
			this.getOrCreateExecutionDetails().setValue(executionDetails);
			return this;
		}
		@Override
		@RosettaAttribute("collateral")
		public ContractBase.ContractBaseBuilder setCollateral(ReferenceWithMetaCollateral collateral) {
			this.collateral = collateral==null?null:collateral.toBuilder();
			return this;
		}
		@Override
		public ContractBase.ContractBaseBuilder setCollateralValue(Collateral collateral) {
			this.getOrCreateCollateral().setValue(collateral);
			return this;
		}
		
		@Override
		public ContractBase build() {
			return new ContractBase.ContractBaseImpl(this);
		}
		
		@Override
		public ContractBase.ContractBaseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ContractBase.ContractBaseBuilder prune() {
			if (contractDetails!=null && !contractDetails.prune().hasData()) contractDetails = null;
			if (executionDetails!=null && !executionDetails.prune().hasData()) executionDetails = null;
			if (collateral!=null && !collateral.prune().hasData()) collateral = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getContractDetails()!=null && getContractDetails().hasData()) return true;
			if (getExecutionDetails()!=null && getExecutionDetails().hasData()) return true;
			if (getCollateral()!=null && getCollateral().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ContractBase.ContractBaseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ContractBase.ContractBaseBuilder o = (ContractBase.ContractBaseBuilder) other;
			
			merger.mergeRosetta(getContractDetails(), o.getContractDetails(), this::setContractDetails);
			merger.mergeRosetta(getExecutionDetails(), o.getExecutionDetails(), this::setExecutionDetails);
			merger.mergeRosetta(getCollateral(), o.getCollateral(), this::setCollateral);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ContractBase _that = getType().cast(o);
		
			if (!Objects.equals(contractDetails, _that.getContractDetails())) return false;
			if (!Objects.equals(executionDetails, _that.getExecutionDetails())) return false;
			if (!Objects.equals(collateral, _that.getCollateral())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (contractDetails != null ? contractDetails.hashCode() : 0);
			_result = 31 * _result + (executionDetails != null ? executionDetails.hashCode() : 0);
			_result = 31 * _result + (collateral != null ? collateral.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ContractBaseBuilder {" +
				"contractDetails=" + this.contractDetails + ", " +
				"executionDetails=" + this.executionDetails + ", " +
				"collateral=" + this.collateral +
			'}';
		}
	}
}
