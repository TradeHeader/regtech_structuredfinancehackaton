package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.QuasiGovernmentIssuerType;
import cdm.base.staticdata.asset.common.QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder;
import cdm.base.staticdata.asset.common.QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilderImpl;
import cdm.base.staticdata.asset.common.QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeImpl;
import cdm.base.staticdata.asset.common.meta.QuasiGovernmentIssuerTypeMeta;
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
 * Represents a class to allow specification of different types of Quasi Government collateral.
 * @version ${project.version}
 */
@RosettaDataType(value="QuasiGovernmentIssuerType", builder=QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilderImpl.class, version="${project.version}")
public interface QuasiGovernmentIssuerType extends RosettaModelObject {

	QuasiGovernmentIssuerTypeMeta metaData = new QuasiGovernmentIssuerTypeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * True if sovereign entity (e.g. not separate legal personality from sovereign) or false if non-sovereign entity (e.g. separate legal personality from sovereign).
	 */
	Boolean getSovereignEntity();
	/**
	 * Applies to non-sovereign entity (e.g. separate legal personality from sovereign).  True if entity has recourse to sovereign (e.g. debt guaranteed by government).  False if entity does not have recourse to sovereign.
	 */
	Boolean getSovereignRecourse();

	/*********************** Build Methods  ***********************/
	QuasiGovernmentIssuerType build();
	
	QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder toBuilder();
	
	static QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder builder() {
		return new QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends QuasiGovernmentIssuerType> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends QuasiGovernmentIssuerType> getType() {
		return QuasiGovernmentIssuerType.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("sovereignEntity"), Boolean.class, getSovereignEntity(), this);
		processor.processBasic(path.newSubPath("sovereignRecourse"), Boolean.class, getSovereignRecourse(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface QuasiGovernmentIssuerTypeBuilder extends QuasiGovernmentIssuerType, RosettaModelObjectBuilder {
		QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder setSovereignEntity(Boolean sovereignEntity);
		QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder setSovereignRecourse(Boolean sovereignRecourse);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("sovereignEntity"), Boolean.class, getSovereignEntity(), this);
			processor.processBasic(path.newSubPath("sovereignRecourse"), Boolean.class, getSovereignRecourse(), this);
		}
		

		QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder prune();
	}

	/*********************** Immutable Implementation of QuasiGovernmentIssuerType  ***********************/
	class QuasiGovernmentIssuerTypeImpl implements QuasiGovernmentIssuerType {
		private final Boolean sovereignEntity;
		private final Boolean sovereignRecourse;
		
		protected QuasiGovernmentIssuerTypeImpl(QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder builder) {
			this.sovereignEntity = builder.getSovereignEntity();
			this.sovereignRecourse = builder.getSovereignRecourse();
		}
		
		@Override
		@RosettaAttribute("sovereignEntity")
		public Boolean getSovereignEntity() {
			return sovereignEntity;
		}
		
		@Override
		@RosettaAttribute("sovereignRecourse")
		public Boolean getSovereignRecourse() {
			return sovereignRecourse;
		}
		
		@Override
		public QuasiGovernmentIssuerType build() {
			return this;
		}
		
		@Override
		public QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder toBuilder() {
			QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder builder) {
			ofNullable(getSovereignEntity()).ifPresent(builder::setSovereignEntity);
			ofNullable(getSovereignRecourse()).ifPresent(builder::setSovereignRecourse);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			QuasiGovernmentIssuerType _that = getType().cast(o);
		
			if (!Objects.equals(sovereignEntity, _that.getSovereignEntity())) return false;
			if (!Objects.equals(sovereignRecourse, _that.getSovereignRecourse())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (sovereignEntity != null ? sovereignEntity.hashCode() : 0);
			_result = 31 * _result + (sovereignRecourse != null ? sovereignRecourse.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "QuasiGovernmentIssuerType {" +
				"sovereignEntity=" + this.sovereignEntity + ", " +
				"sovereignRecourse=" + this.sovereignRecourse +
			'}';
		}
	}

	/*********************** Builder Implementation of QuasiGovernmentIssuerType  ***********************/
	class QuasiGovernmentIssuerTypeBuilderImpl implements QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder {
	
		protected Boolean sovereignEntity;
		protected Boolean sovereignRecourse;
	
		public QuasiGovernmentIssuerTypeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("sovereignEntity")
		public Boolean getSovereignEntity() {
			return sovereignEntity;
		}
		
		@Override
		@RosettaAttribute("sovereignRecourse")
		public Boolean getSovereignRecourse() {
			return sovereignRecourse;
		}
		
	
		@Override
		@RosettaAttribute("sovereignEntity")
		public QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder setSovereignEntity(Boolean sovereignEntity) {
			this.sovereignEntity = sovereignEntity==null?null:sovereignEntity;
			return this;
		}
		@Override
		@RosettaAttribute("sovereignRecourse")
		public QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder setSovereignRecourse(Boolean sovereignRecourse) {
			this.sovereignRecourse = sovereignRecourse==null?null:sovereignRecourse;
			return this;
		}
		
		@Override
		public QuasiGovernmentIssuerType build() {
			return new QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeImpl(this);
		}
		
		@Override
		public QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSovereignEntity()!=null) return true;
			if (getSovereignRecourse()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder o = (QuasiGovernmentIssuerType.QuasiGovernmentIssuerTypeBuilder) other;
			
			
			merger.mergeBasic(getSovereignEntity(), o.getSovereignEntity(), this::setSovereignEntity);
			merger.mergeBasic(getSovereignRecourse(), o.getSovereignRecourse(), this::setSovereignRecourse);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			QuasiGovernmentIssuerType _that = getType().cast(o);
		
			if (!Objects.equals(sovereignEntity, _that.getSovereignEntity())) return false;
			if (!Objects.equals(sovereignRecourse, _that.getSovereignRecourse())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (sovereignEntity != null ? sovereignEntity.hashCode() : 0);
			_result = 31 * _result + (sovereignRecourse != null ? sovereignRecourse.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "QuasiGovernmentIssuerTypeBuilder {" +
				"sovereignEntity=" + this.sovereignEntity + ", " +
				"sovereignRecourse=" + this.sovereignRecourse +
			'}';
		}
	}
}
