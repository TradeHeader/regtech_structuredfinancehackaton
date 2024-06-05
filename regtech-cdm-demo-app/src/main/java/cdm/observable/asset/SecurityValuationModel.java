package cdm.observable.asset;

import cdm.observable.asset.BondValuationModel;
import cdm.observable.asset.SecurityValuationModel;
import cdm.observable.asset.SecurityValuationModel.SecurityValuationModelBuilder;
import cdm.observable.asset.SecurityValuationModel.SecurityValuationModelBuilderImpl;
import cdm.observable.asset.SecurityValuationModel.SecurityValuationModelImpl;
import cdm.observable.asset.UnitContractValuationModel;
import cdm.observable.asset.meta.SecurityValuationModelMeta;
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
 *  The security valuation model choice, which can either be based on nominal amount as for a bond, or on the number of contract units as for equity.
 * @version ${project.version}
 */
@RosettaDataType(value="SecurityValuationModel", builder=SecurityValuationModel.SecurityValuationModelBuilderImpl.class, version="${project.version}")
public interface SecurityValuationModel extends RosettaModelObject {

	SecurityValuationModelMeta metaData = new SecurityValuationModelMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The valuation model when the security is a bond.
	 */
	BondValuationModel getBondValuationModel();
	/**
	 * The valuation model when the security is a unit contract like equity.
	 */
	UnitContractValuationModel getUnitContractValuationModel();

	/*********************** Build Methods  ***********************/
	SecurityValuationModel build();
	
	SecurityValuationModel.SecurityValuationModelBuilder toBuilder();
	
	static SecurityValuationModel.SecurityValuationModelBuilder builder() {
		return new SecurityValuationModel.SecurityValuationModelBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SecurityValuationModel> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SecurityValuationModel> getType() {
		return SecurityValuationModel.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("bondValuationModel"), processor, BondValuationModel.class, getBondValuationModel());
		processRosetta(path.newSubPath("unitContractValuationModel"), processor, UnitContractValuationModel.class, getUnitContractValuationModel());
	}
	

	/*********************** Builder Interface  ***********************/
	interface SecurityValuationModelBuilder extends SecurityValuationModel, RosettaModelObjectBuilder {
		BondValuationModel.BondValuationModelBuilder getOrCreateBondValuationModel();
		BondValuationModel.BondValuationModelBuilder getBondValuationModel();
		UnitContractValuationModel.UnitContractValuationModelBuilder getOrCreateUnitContractValuationModel();
		UnitContractValuationModel.UnitContractValuationModelBuilder getUnitContractValuationModel();
		SecurityValuationModel.SecurityValuationModelBuilder setBondValuationModel(BondValuationModel bondValuationModel);
		SecurityValuationModel.SecurityValuationModelBuilder setUnitContractValuationModel(UnitContractValuationModel unitContractValuationModel);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("bondValuationModel"), processor, BondValuationModel.BondValuationModelBuilder.class, getBondValuationModel());
			processRosetta(path.newSubPath("unitContractValuationModel"), processor, UnitContractValuationModel.UnitContractValuationModelBuilder.class, getUnitContractValuationModel());
		}
		

		SecurityValuationModel.SecurityValuationModelBuilder prune();
	}

	/*********************** Immutable Implementation of SecurityValuationModel  ***********************/
	class SecurityValuationModelImpl implements SecurityValuationModel {
		private final BondValuationModel bondValuationModel;
		private final UnitContractValuationModel unitContractValuationModel;
		
		protected SecurityValuationModelImpl(SecurityValuationModel.SecurityValuationModelBuilder builder) {
			this.bondValuationModel = ofNullable(builder.getBondValuationModel()).map(f->f.build()).orElse(null);
			this.unitContractValuationModel = ofNullable(builder.getUnitContractValuationModel()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("bondValuationModel")
		public BondValuationModel getBondValuationModel() {
			return bondValuationModel;
		}
		
		@Override
		@RosettaAttribute("unitContractValuationModel")
		public UnitContractValuationModel getUnitContractValuationModel() {
			return unitContractValuationModel;
		}
		
		@Override
		public SecurityValuationModel build() {
			return this;
		}
		
		@Override
		public SecurityValuationModel.SecurityValuationModelBuilder toBuilder() {
			SecurityValuationModel.SecurityValuationModelBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SecurityValuationModel.SecurityValuationModelBuilder builder) {
			ofNullable(getBondValuationModel()).ifPresent(builder::setBondValuationModel);
			ofNullable(getUnitContractValuationModel()).ifPresent(builder::setUnitContractValuationModel);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SecurityValuationModel _that = getType().cast(o);
		
			if (!Objects.equals(bondValuationModel, _that.getBondValuationModel())) return false;
			if (!Objects.equals(unitContractValuationModel, _that.getUnitContractValuationModel())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (bondValuationModel != null ? bondValuationModel.hashCode() : 0);
			_result = 31 * _result + (unitContractValuationModel != null ? unitContractValuationModel.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SecurityValuationModel {" +
				"bondValuationModel=" + this.bondValuationModel + ", " +
				"unitContractValuationModel=" + this.unitContractValuationModel +
			'}';
		}
	}

	/*********************** Builder Implementation of SecurityValuationModel  ***********************/
	class SecurityValuationModelBuilderImpl implements SecurityValuationModel.SecurityValuationModelBuilder {
	
		protected BondValuationModel.BondValuationModelBuilder bondValuationModel;
		protected UnitContractValuationModel.UnitContractValuationModelBuilder unitContractValuationModel;
	
		public SecurityValuationModelBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("bondValuationModel")
		public BondValuationModel.BondValuationModelBuilder getBondValuationModel() {
			return bondValuationModel;
		}
		
		@Override
		public BondValuationModel.BondValuationModelBuilder getOrCreateBondValuationModel() {
			BondValuationModel.BondValuationModelBuilder result;
			if (bondValuationModel!=null) {
				result = bondValuationModel;
			}
			else {
				result = bondValuationModel = BondValuationModel.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("unitContractValuationModel")
		public UnitContractValuationModel.UnitContractValuationModelBuilder getUnitContractValuationModel() {
			return unitContractValuationModel;
		}
		
		@Override
		public UnitContractValuationModel.UnitContractValuationModelBuilder getOrCreateUnitContractValuationModel() {
			UnitContractValuationModel.UnitContractValuationModelBuilder result;
			if (unitContractValuationModel!=null) {
				result = unitContractValuationModel;
			}
			else {
				result = unitContractValuationModel = UnitContractValuationModel.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("bondValuationModel")
		public SecurityValuationModel.SecurityValuationModelBuilder setBondValuationModel(BondValuationModel bondValuationModel) {
			this.bondValuationModel = bondValuationModel==null?null:bondValuationModel.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("unitContractValuationModel")
		public SecurityValuationModel.SecurityValuationModelBuilder setUnitContractValuationModel(UnitContractValuationModel unitContractValuationModel) {
			this.unitContractValuationModel = unitContractValuationModel==null?null:unitContractValuationModel.toBuilder();
			return this;
		}
		
		@Override
		public SecurityValuationModel build() {
			return new SecurityValuationModel.SecurityValuationModelImpl(this);
		}
		
		@Override
		public SecurityValuationModel.SecurityValuationModelBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SecurityValuationModel.SecurityValuationModelBuilder prune() {
			if (bondValuationModel!=null && !bondValuationModel.prune().hasData()) bondValuationModel = null;
			if (unitContractValuationModel!=null && !unitContractValuationModel.prune().hasData()) unitContractValuationModel = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getBondValuationModel()!=null && getBondValuationModel().hasData()) return true;
			if (getUnitContractValuationModel()!=null && getUnitContractValuationModel().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SecurityValuationModel.SecurityValuationModelBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			SecurityValuationModel.SecurityValuationModelBuilder o = (SecurityValuationModel.SecurityValuationModelBuilder) other;
			
			merger.mergeRosetta(getBondValuationModel(), o.getBondValuationModel(), this::setBondValuationModel);
			merger.mergeRosetta(getUnitContractValuationModel(), o.getUnitContractValuationModel(), this::setUnitContractValuationModel);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SecurityValuationModel _that = getType().cast(o);
		
			if (!Objects.equals(bondValuationModel, _that.getBondValuationModel())) return false;
			if (!Objects.equals(unitContractValuationModel, _that.getUnitContractValuationModel())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (bondValuationModel != null ? bondValuationModel.hashCode() : 0);
			_result = 31 * _result + (unitContractValuationModel != null ? unitContractValuationModel.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SecurityValuationModelBuilder {" +
				"bondValuationModel=" + this.bondValuationModel + ", " +
				"unitContractValuationModel=" + this.unitContractValuationModel +
			'}';
		}
	}
}
