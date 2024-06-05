package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.CollateralIssuerType;
import cdm.base.staticdata.asset.common.CollateralIssuerType.CollateralIssuerTypeBuilder;
import cdm.base.staticdata.asset.common.CollateralIssuerType.CollateralIssuerTypeBuilderImpl;
import cdm.base.staticdata.asset.common.CollateralIssuerType.CollateralIssuerTypeImpl;
import cdm.base.staticdata.asset.common.IssuerTypeEnum;
import cdm.base.staticdata.asset.common.QuasiGovernmentIssuerType;
import cdm.base.staticdata.asset.common.RegionalGovernmentIssuerType;
import cdm.base.staticdata.asset.common.SpecialPurposeVehicleIssuerType;
import cdm.base.staticdata.asset.common.SupraNationalIssuerTypeEnum;
import cdm.base.staticdata.asset.common.meta.CollateralIssuerTypeMeta;
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
 * Represents a class to allow specification of the type of entity issuing the collateral.
 * @version ${project.version}
 */
@RosettaDataType(value="CollateralIssuerType", builder=CollateralIssuerType.CollateralIssuerTypeBuilderImpl.class, version="${project.version}")
public interface CollateralIssuerType extends RosettaModelObject {

	CollateralIssuerTypeMeta metaData = new CollateralIssuerTypeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the origin of entity issuing the collateral.
	 */
	IssuerTypeEnum getIssuerType();
	/**
	 * Specifies debt issued by international organisations and multilateral banks.
	 */
	SupraNationalIssuerTypeEnum getSupraNationalType();
	/**
	 * Specifies debt issues by institutions or bodies, typically constituted by statute, with a function mandated by the government and subject to government supervision inclusive of profit- and non-profit making bodies. Includes the US Agencies and GSEs and the EU concept of public sector entities. Excluding any entities which are also Regional Government.
	 */
	QuasiGovernmentIssuerType getQuasiGovernmentType();
	/**
	 * Specifies Regional government, local authority or municipal.
	 */
	RegionalGovernmentIssuerType getRegionalGovernmentType();
	/**
	 * Specifies a subsidiary company that is formed to undertake a specific business purpose of acquisition and financing of specific assets on a potentially limited recourse basis dependent of how it is designed. E.g. asset backed securities, including securitisations.
	 */
	SpecialPurposeVehicleIssuerType getSpecialPurposeVehicleType();

	/*********************** Build Methods  ***********************/
	CollateralIssuerType build();
	
	CollateralIssuerType.CollateralIssuerTypeBuilder toBuilder();
	
	static CollateralIssuerType.CollateralIssuerTypeBuilder builder() {
		return new CollateralIssuerType.CollateralIssuerTypeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CollateralIssuerType> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CollateralIssuerType> getType() {
		return CollateralIssuerType.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("issuerType"), IssuerTypeEnum.class, getIssuerType(), this);
		processor.processBasic(path.newSubPath("supraNationalType"), SupraNationalIssuerTypeEnum.class, getSupraNationalType(), this);
		processRosetta(path.newSubPath("quasiGovernmentType"), processor, QuasiGovernmentIssuerType.class, getQuasiGovernmentType());
		processRosetta(path.newSubPath("regionalGovernmentType"), processor, RegionalGovernmentIssuerType.class, getRegionalGovernmentType());
		processRosetta(path.newSubPath("specialPurposeVehicleType"), processor, SpecialPurposeVehicleIssuerType.class, getSpecialPurposeVehicleType());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CollateralIssuerTypeBuilder extends CollateralIssuerType, RosettaModelObjectBuilder {
		QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder getOrCreateQuasiGovernmentType();
		QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder getQuasiGovernmentType();
		RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder getOrCreateRegionalGovernmentType();
		RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder getRegionalGovernmentType();
		SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder getOrCreateSpecialPurposeVehicleType();
		SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder getSpecialPurposeVehicleType();
		CollateralIssuerType.CollateralIssuerTypeBuilder setIssuerType(IssuerTypeEnum issuerType);
		CollateralIssuerType.CollateralIssuerTypeBuilder setSupraNationalType(SupraNationalIssuerTypeEnum supraNationalType);
		CollateralIssuerType.CollateralIssuerTypeBuilder setQuasiGovernmentType(QuasiGovernmentIssuerType quasiGovernmentType);
		CollateralIssuerType.CollateralIssuerTypeBuilder setRegionalGovernmentType(RegionalGovernmentIssuerType regionalGovernmentType);
		CollateralIssuerType.CollateralIssuerTypeBuilder setSpecialPurposeVehicleType(SpecialPurposeVehicleIssuerType specialPurposeVehicleType);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("issuerType"), IssuerTypeEnum.class, getIssuerType(), this);
			processor.processBasic(path.newSubPath("supraNationalType"), SupraNationalIssuerTypeEnum.class, getSupraNationalType(), this);
			processRosetta(path.newSubPath("quasiGovernmentType"), processor, QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder.class, getQuasiGovernmentType());
			processRosetta(path.newSubPath("regionalGovernmentType"), processor, RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder.class, getRegionalGovernmentType());
			processRosetta(path.newSubPath("specialPurposeVehicleType"), processor, SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder.class, getSpecialPurposeVehicleType());
		}
		

		CollateralIssuerType.CollateralIssuerTypeBuilder prune();
	}

	/*********************** Immutable Implementation of CollateralIssuerType  ***********************/
	class CollateralIssuerTypeImpl implements CollateralIssuerType {
		private final IssuerTypeEnum issuerType;
		private final SupraNationalIssuerTypeEnum supraNationalType;
		private final QuasiGovernmentIssuerType quasiGovernmentType;
		private final RegionalGovernmentIssuerType regionalGovernmentType;
		private final SpecialPurposeVehicleIssuerType specialPurposeVehicleType;
		
		protected CollateralIssuerTypeImpl(CollateralIssuerType.CollateralIssuerTypeBuilder builder) {
			this.issuerType = builder.getIssuerType();
			this.supraNationalType = builder.getSupraNationalType();
			this.quasiGovernmentType = ofNullable(builder.getQuasiGovernmentType()).map(f->f.build()).orElse(null);
			this.regionalGovernmentType = ofNullable(builder.getRegionalGovernmentType()).map(f->f.build()).orElse(null);
			this.specialPurposeVehicleType = ofNullable(builder.getSpecialPurposeVehicleType()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("issuerType")
		public IssuerTypeEnum getIssuerType() {
			return issuerType;
		}
		
		@Override
		@RosettaAttribute("supraNationalType")
		public SupraNationalIssuerTypeEnum getSupraNationalType() {
			return supraNationalType;
		}
		
		@Override
		@RosettaAttribute("quasiGovernmentType")
		public QuasiGovernmentIssuerType getQuasiGovernmentType() {
			return quasiGovernmentType;
		}
		
		@Override
		@RosettaAttribute("regionalGovernmentType")
		public RegionalGovernmentIssuerType getRegionalGovernmentType() {
			return regionalGovernmentType;
		}
		
		@Override
		@RosettaAttribute("specialPurposeVehicleType")
		public SpecialPurposeVehicleIssuerType getSpecialPurposeVehicleType() {
			return specialPurposeVehicleType;
		}
		
		@Override
		public CollateralIssuerType build() {
			return this;
		}
		
		@Override
		public CollateralIssuerType.CollateralIssuerTypeBuilder toBuilder() {
			CollateralIssuerType.CollateralIssuerTypeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CollateralIssuerType.CollateralIssuerTypeBuilder builder) {
			ofNullable(getIssuerType()).ifPresent(builder::setIssuerType);
			ofNullable(getSupraNationalType()).ifPresent(builder::setSupraNationalType);
			ofNullable(getQuasiGovernmentType()).ifPresent(builder::setQuasiGovernmentType);
			ofNullable(getRegionalGovernmentType()).ifPresent(builder::setRegionalGovernmentType);
			ofNullable(getSpecialPurposeVehicleType()).ifPresent(builder::setSpecialPurposeVehicleType);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralIssuerType _that = getType().cast(o);
		
			if (!Objects.equals(issuerType, _that.getIssuerType())) return false;
			if (!Objects.equals(supraNationalType, _that.getSupraNationalType())) return false;
			if (!Objects.equals(quasiGovernmentType, _that.getQuasiGovernmentType())) return false;
			if (!Objects.equals(regionalGovernmentType, _that.getRegionalGovernmentType())) return false;
			if (!Objects.equals(specialPurposeVehicleType, _that.getSpecialPurposeVehicleType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (issuerType != null ? issuerType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (supraNationalType != null ? supraNationalType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (quasiGovernmentType != null ? quasiGovernmentType.hashCode() : 0);
			_result = 31 * _result + (regionalGovernmentType != null ? regionalGovernmentType.hashCode() : 0);
			_result = 31 * _result + (specialPurposeVehicleType != null ? specialPurposeVehicleType.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralIssuerType {" +
				"issuerType=" + this.issuerType + ", " +
				"supraNationalType=" + this.supraNationalType + ", " +
				"quasiGovernmentType=" + this.quasiGovernmentType + ", " +
				"regionalGovernmentType=" + this.regionalGovernmentType + ", " +
				"specialPurposeVehicleType=" + this.specialPurposeVehicleType +
			'}';
		}
	}

	/*********************** Builder Implementation of CollateralIssuerType  ***********************/
	class CollateralIssuerTypeBuilderImpl implements CollateralIssuerType.CollateralIssuerTypeBuilder {
	
		protected IssuerTypeEnum issuerType;
		protected SupraNationalIssuerTypeEnum supraNationalType;
		protected QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder quasiGovernmentType;
		protected RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder regionalGovernmentType;
		protected SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder specialPurposeVehicleType;
	
		public CollateralIssuerTypeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("issuerType")
		public IssuerTypeEnum getIssuerType() {
			return issuerType;
		}
		
		@Override
		@RosettaAttribute("supraNationalType")
		public SupraNationalIssuerTypeEnum getSupraNationalType() {
			return supraNationalType;
		}
		
		@Override
		@RosettaAttribute("quasiGovernmentType")
		public QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder getQuasiGovernmentType() {
			return quasiGovernmentType;
		}
		
		@Override
		public QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder getOrCreateQuasiGovernmentType() {
			QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder result;
			if (quasiGovernmentType!=null) {
				result = quasiGovernmentType;
			}
			else {
				result = quasiGovernmentType = QuasiGovernmentIssuerType.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("regionalGovernmentType")
		public RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder getRegionalGovernmentType() {
			return regionalGovernmentType;
		}
		
		@Override
		public RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder getOrCreateRegionalGovernmentType() {
			RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder result;
			if (regionalGovernmentType!=null) {
				result = regionalGovernmentType;
			}
			else {
				result = regionalGovernmentType = RegionalGovernmentIssuerType.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("specialPurposeVehicleType")
		public SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder getSpecialPurposeVehicleType() {
			return specialPurposeVehicleType;
		}
		
		@Override
		public SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder getOrCreateSpecialPurposeVehicleType() {
			SpecialPurposeVehicleIssuerType.SpecialPurposeVehicleIssuerTypeBuilder result;
			if (specialPurposeVehicleType!=null) {
				result = specialPurposeVehicleType;
			}
			else {
				result = specialPurposeVehicleType = SpecialPurposeVehicleIssuerType.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("issuerType")
		public CollateralIssuerType.CollateralIssuerTypeBuilder setIssuerType(IssuerTypeEnum issuerType) {
			this.issuerType = issuerType==null?null:issuerType;
			return this;
		}
		@Override
		@RosettaAttribute("supraNationalType")
		public CollateralIssuerType.CollateralIssuerTypeBuilder setSupraNationalType(SupraNationalIssuerTypeEnum supraNationalType) {
			this.supraNationalType = supraNationalType==null?null:supraNationalType;
			return this;
		}
		@Override
		@RosettaAttribute("quasiGovernmentType")
		public CollateralIssuerType.CollateralIssuerTypeBuilder setQuasiGovernmentType(QuasiGovernmentIssuerType quasiGovernmentType) {
			this.quasiGovernmentType = quasiGovernmentType==null?null:quasiGovernmentType.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("regionalGovernmentType")
		public CollateralIssuerType.CollateralIssuerTypeBuilder setRegionalGovernmentType(RegionalGovernmentIssuerType regionalGovernmentType) {
			this.regionalGovernmentType = regionalGovernmentType==null?null:regionalGovernmentType.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("specialPurposeVehicleType")
		public CollateralIssuerType.CollateralIssuerTypeBuilder setSpecialPurposeVehicleType(SpecialPurposeVehicleIssuerType specialPurposeVehicleType) {
			this.specialPurposeVehicleType = specialPurposeVehicleType==null?null:specialPurposeVehicleType.toBuilder();
			return this;
		}
		
		@Override
		public CollateralIssuerType build() {
			return new CollateralIssuerType.CollateralIssuerTypeImpl(this);
		}
		
		@Override
		public CollateralIssuerType.CollateralIssuerTypeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralIssuerType.CollateralIssuerTypeBuilder prune() {
			if (quasiGovernmentType!=null && !quasiGovernmentType.prune().hasData()) quasiGovernmentType = null;
			if (regionalGovernmentType!=null && !regionalGovernmentType.prune().hasData()) regionalGovernmentType = null;
			if (specialPurposeVehicleType!=null && !specialPurposeVehicleType.prune().hasData()) specialPurposeVehicleType = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIssuerType()!=null) return true;
			if (getSupraNationalType()!=null) return true;
			if (getQuasiGovernmentType()!=null && getQuasiGovernmentType().hasData()) return true;
			if (getRegionalGovernmentType()!=null && getRegionalGovernmentType().hasData()) return true;
			if (getSpecialPurposeVehicleType()!=null && getSpecialPurposeVehicleType().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralIssuerType.CollateralIssuerTypeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CollateralIssuerType.CollateralIssuerTypeBuilder o = (CollateralIssuerType.CollateralIssuerTypeBuilder) other;
			
			merger.mergeRosetta(getQuasiGovernmentType(), o.getQuasiGovernmentType(), this::setQuasiGovernmentType);
			merger.mergeRosetta(getRegionalGovernmentType(), o.getRegionalGovernmentType(), this::setRegionalGovernmentType);
			merger.mergeRosetta(getSpecialPurposeVehicleType(), o.getSpecialPurposeVehicleType(), this::setSpecialPurposeVehicleType);
			
			merger.mergeBasic(getIssuerType(), o.getIssuerType(), this::setIssuerType);
			merger.mergeBasic(getSupraNationalType(), o.getSupraNationalType(), this::setSupraNationalType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralIssuerType _that = getType().cast(o);
		
			if (!Objects.equals(issuerType, _that.getIssuerType())) return false;
			if (!Objects.equals(supraNationalType, _that.getSupraNationalType())) return false;
			if (!Objects.equals(quasiGovernmentType, _that.getQuasiGovernmentType())) return false;
			if (!Objects.equals(regionalGovernmentType, _that.getRegionalGovernmentType())) return false;
			if (!Objects.equals(specialPurposeVehicleType, _that.getSpecialPurposeVehicleType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (issuerType != null ? issuerType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (supraNationalType != null ? supraNationalType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (quasiGovernmentType != null ? quasiGovernmentType.hashCode() : 0);
			_result = 31 * _result + (regionalGovernmentType != null ? regionalGovernmentType.hashCode() : 0);
			_result = 31 * _result + (specialPurposeVehicleType != null ? specialPurposeVehicleType.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralIssuerTypeBuilder {" +
				"issuerType=" + this.issuerType + ", " +
				"supraNationalType=" + this.supraNationalType + ", " +
				"quasiGovernmentType=" + this.quasiGovernmentType + ", " +
				"regionalGovernmentType=" + this.regionalGovernmentType + ", " +
				"specialPurposeVehicleType=" + this.specialPurposeVehicleType +
			'}';
		}
	}
}
