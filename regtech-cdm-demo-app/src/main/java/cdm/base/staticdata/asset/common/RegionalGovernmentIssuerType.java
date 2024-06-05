package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.RegionalGovernmentIssuerType;
import cdm.base.staticdata.asset.common.RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder;
import cdm.base.staticdata.asset.common.RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilderImpl;
import cdm.base.staticdata.asset.common.RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeImpl;
import cdm.base.staticdata.asset.common.meta.RegionalGovernmentIssuerTypeMeta;
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
 * Represents a class to allow specification of different type of Regional government collateral.
 * @version ${project.version}
 */
@RosettaDataType(value="RegionalGovernmentIssuerType", builder=RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilderImpl.class, version="${project.version}")
public interface RegionalGovernmentIssuerType extends RosettaModelObject {

	RegionalGovernmentIssuerTypeMeta metaData = new RegionalGovernmentIssuerTypeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Applies to regional governments, local authorities or municipals.  True if entity has recourse to sovereign (e.g. debt guaranteed by government).  False if entity does not have recourse to sovereign.
	 */
	Boolean getSovereignRecourse();

	/*********************** Build Methods  ***********************/
	RegionalGovernmentIssuerType build();
	
	RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder toBuilder();
	
	static RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder builder() {
		return new RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends RegionalGovernmentIssuerType> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends RegionalGovernmentIssuerType> getType() {
		return RegionalGovernmentIssuerType.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("sovereignRecourse"), Boolean.class, getSovereignRecourse(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface RegionalGovernmentIssuerTypeBuilder extends RegionalGovernmentIssuerType, RosettaModelObjectBuilder {
		RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder setSovereignRecourse(Boolean sovereignRecourse);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("sovereignRecourse"), Boolean.class, getSovereignRecourse(), this);
		}
		

		RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder prune();
	}

	/*********************** Immutable Implementation of RegionalGovernmentIssuerType  ***********************/
	class RegionalGovernmentIssuerTypeImpl implements RegionalGovernmentIssuerType {
		private final Boolean sovereignRecourse;
		
		protected RegionalGovernmentIssuerTypeImpl(RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder builder) {
			this.sovereignRecourse = builder.getSovereignRecourse();
		}
		
		@Override
		@RosettaAttribute("sovereignRecourse")
		public Boolean getSovereignRecourse() {
			return sovereignRecourse;
		}
		
		@Override
		public RegionalGovernmentIssuerType build() {
			return this;
		}
		
		@Override
		public RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder toBuilder() {
			RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder builder) {
			ofNullable(getSovereignRecourse()).ifPresent(builder::setSovereignRecourse);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			RegionalGovernmentIssuerType _that = getType().cast(o);
		
			if (!Objects.equals(sovereignRecourse, _that.getSovereignRecourse())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (sovereignRecourse != null ? sovereignRecourse.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "RegionalGovernmentIssuerType {" +
				"sovereignRecourse=" + this.sovereignRecourse +
			'}';
		}
	}

	/*********************** Builder Implementation of RegionalGovernmentIssuerType  ***********************/
	class RegionalGovernmentIssuerTypeBuilderImpl implements RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder {
	
		protected Boolean sovereignRecourse;
	
		public RegionalGovernmentIssuerTypeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("sovereignRecourse")
		public Boolean getSovereignRecourse() {
			return sovereignRecourse;
		}
		
	
		@Override
		@RosettaAttribute("sovereignRecourse")
		public RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder setSovereignRecourse(Boolean sovereignRecourse) {
			this.sovereignRecourse = sovereignRecourse==null?null:sovereignRecourse;
			return this;
		}
		
		@Override
		public RegionalGovernmentIssuerType build() {
			return new RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeImpl(this);
		}
		
		@Override
		public RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSovereignRecourse()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder o = (RegionalGovernmentIssuerType.RegionalGovernmentIssuerTypeBuilder) other;
			
			
			merger.mergeBasic(getSovereignRecourse(), o.getSovereignRecourse(), this::setSovereignRecourse);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			RegionalGovernmentIssuerType _that = getType().cast(o);
		
			if (!Objects.equals(sovereignRecourse, _that.getSovereignRecourse())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (sovereignRecourse != null ? sovereignRecourse.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "RegionalGovernmentIssuerTypeBuilder {" +
				"sovereignRecourse=" + this.sovereignRecourse +
			'}';
		}
	}
}
