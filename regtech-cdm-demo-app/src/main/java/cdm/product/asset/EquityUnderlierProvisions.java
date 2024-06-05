package cdm.product.asset;

import cdm.product.asset.EquityUnderlierProvisions;
import cdm.product.asset.EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder;
import cdm.product.asset.EquityUnderlierProvisions.EquityUnderlierProvisionsBuilderImpl;
import cdm.product.asset.EquityUnderlierProvisions.EquityUnderlierProvisionsImpl;
import cdm.product.asset.meta.EquityUnderlierProvisionsMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="EquityUnderlierProvisions", builder=EquityUnderlierProvisions.EquityUnderlierProvisionsBuilderImpl.class, version="${project.version}")
public interface EquityUnderlierProvisions extends RosettaModelObject {

	EquityUnderlierProvisionsMeta metaData = new EquityUnderlierProvisionsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * For an index option or swap transaction, a flag to indicate whether a relevant Multiple Exchange Index Annex is applicable to the transaction. This annex defines additional provisions which are applicable where an index is comprised of component securities that are traded on multiple exchanges.
	 */
	Boolean getMultipleExchangeIndexAnnexFallback();
	/**
	 * For an index option or swap transaction, a flag to indicate whether a relevant Component Security Index Annex is applicable to the transaction.
	 */
	Boolean getComponentSecurityIndexAnnexFallback();
	/**
	 * The ISO 3166 standard code for the country within which the postal address is located.
	 */
	FieldWithMetaString getLocalJurisdiction();
	/**
	 * The ISO 3166 standard code for the country within which the postal address is located.
	 */
	FieldWithMetaString getRelevantJurisdiction();

	/*********************** Build Methods  ***********************/
	EquityUnderlierProvisions build();
	
	EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder toBuilder();
	
	static EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder builder() {
		return new EquityUnderlierProvisions.EquityUnderlierProvisionsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends EquityUnderlierProvisions> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends EquityUnderlierProvisions> getType() {
		return EquityUnderlierProvisions.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("multipleExchangeIndexAnnexFallback"), Boolean.class, getMultipleExchangeIndexAnnexFallback(), this);
		processor.processBasic(path.newSubPath("componentSecurityIndexAnnexFallback"), Boolean.class, getComponentSecurityIndexAnnexFallback(), this);
		processRosetta(path.newSubPath("localJurisdiction"), processor, FieldWithMetaString.class, getLocalJurisdiction());
		processRosetta(path.newSubPath("relevantJurisdiction"), processor, FieldWithMetaString.class, getRelevantJurisdiction());
	}
	

	/*********************** Builder Interface  ***********************/
	interface EquityUnderlierProvisionsBuilder extends EquityUnderlierProvisions, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateLocalJurisdiction();
		FieldWithMetaString.FieldWithMetaStringBuilder getLocalJurisdiction();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateRelevantJurisdiction();
		FieldWithMetaString.FieldWithMetaStringBuilder getRelevantJurisdiction();
		EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder setMultipleExchangeIndexAnnexFallback(Boolean multipleExchangeIndexAnnexFallback);
		EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder setComponentSecurityIndexAnnexFallback(Boolean componentSecurityIndexAnnexFallback);
		EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder setLocalJurisdiction(FieldWithMetaString localJurisdiction0);
		EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder setLocalJurisdictionValue(String localJurisdiction1);
		EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder setRelevantJurisdiction(FieldWithMetaString relevantJurisdiction0);
		EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder setRelevantJurisdictionValue(String relevantJurisdiction1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("multipleExchangeIndexAnnexFallback"), Boolean.class, getMultipleExchangeIndexAnnexFallback(), this);
			processor.processBasic(path.newSubPath("componentSecurityIndexAnnexFallback"), Boolean.class, getComponentSecurityIndexAnnexFallback(), this);
			processRosetta(path.newSubPath("localJurisdiction"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getLocalJurisdiction());
			processRosetta(path.newSubPath("relevantJurisdiction"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getRelevantJurisdiction());
		}
		

		EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder prune();
	}

	/*********************** Immutable Implementation of EquityUnderlierProvisions  ***********************/
	class EquityUnderlierProvisionsImpl implements EquityUnderlierProvisions {
		private final Boolean multipleExchangeIndexAnnexFallback;
		private final Boolean componentSecurityIndexAnnexFallback;
		private final FieldWithMetaString localJurisdiction;
		private final FieldWithMetaString relevantJurisdiction;
		
		protected EquityUnderlierProvisionsImpl(EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder builder) {
			this.multipleExchangeIndexAnnexFallback = builder.getMultipleExchangeIndexAnnexFallback();
			this.componentSecurityIndexAnnexFallback = builder.getComponentSecurityIndexAnnexFallback();
			this.localJurisdiction = ofNullable(builder.getLocalJurisdiction()).map(f->f.build()).orElse(null);
			this.relevantJurisdiction = ofNullable(builder.getRelevantJurisdiction()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("multipleExchangeIndexAnnexFallback")
		public Boolean getMultipleExchangeIndexAnnexFallback() {
			return multipleExchangeIndexAnnexFallback;
		}
		
		@Override
		@RosettaAttribute("componentSecurityIndexAnnexFallback")
		public Boolean getComponentSecurityIndexAnnexFallback() {
			return componentSecurityIndexAnnexFallback;
		}
		
		@Override
		@RosettaAttribute("localJurisdiction")
		public FieldWithMetaString getLocalJurisdiction() {
			return localJurisdiction;
		}
		
		@Override
		@RosettaAttribute("relevantJurisdiction")
		public FieldWithMetaString getRelevantJurisdiction() {
			return relevantJurisdiction;
		}
		
		@Override
		public EquityUnderlierProvisions build() {
			return this;
		}
		
		@Override
		public EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder toBuilder() {
			EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder builder) {
			ofNullable(getMultipleExchangeIndexAnnexFallback()).ifPresent(builder::setMultipleExchangeIndexAnnexFallback);
			ofNullable(getComponentSecurityIndexAnnexFallback()).ifPresent(builder::setComponentSecurityIndexAnnexFallback);
			ofNullable(getLocalJurisdiction()).ifPresent(builder::setLocalJurisdiction);
			ofNullable(getRelevantJurisdiction()).ifPresent(builder::setRelevantJurisdiction);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			EquityUnderlierProvisions _that = getType().cast(o);
		
			if (!Objects.equals(multipleExchangeIndexAnnexFallback, _that.getMultipleExchangeIndexAnnexFallback())) return false;
			if (!Objects.equals(componentSecurityIndexAnnexFallback, _that.getComponentSecurityIndexAnnexFallback())) return false;
			if (!Objects.equals(localJurisdiction, _that.getLocalJurisdiction())) return false;
			if (!Objects.equals(relevantJurisdiction, _that.getRelevantJurisdiction())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (multipleExchangeIndexAnnexFallback != null ? multipleExchangeIndexAnnexFallback.hashCode() : 0);
			_result = 31 * _result + (componentSecurityIndexAnnexFallback != null ? componentSecurityIndexAnnexFallback.hashCode() : 0);
			_result = 31 * _result + (localJurisdiction != null ? localJurisdiction.hashCode() : 0);
			_result = 31 * _result + (relevantJurisdiction != null ? relevantJurisdiction.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EquityUnderlierProvisions {" +
				"multipleExchangeIndexAnnexFallback=" + this.multipleExchangeIndexAnnexFallback + ", " +
				"componentSecurityIndexAnnexFallback=" + this.componentSecurityIndexAnnexFallback + ", " +
				"localJurisdiction=" + this.localJurisdiction + ", " +
				"relevantJurisdiction=" + this.relevantJurisdiction +
			'}';
		}
	}

	/*********************** Builder Implementation of EquityUnderlierProvisions  ***********************/
	class EquityUnderlierProvisionsBuilderImpl implements EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder {
	
		protected Boolean multipleExchangeIndexAnnexFallback;
		protected Boolean componentSecurityIndexAnnexFallback;
		protected FieldWithMetaString.FieldWithMetaStringBuilder localJurisdiction;
		protected FieldWithMetaString.FieldWithMetaStringBuilder relevantJurisdiction;
	
		public EquityUnderlierProvisionsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("multipleExchangeIndexAnnexFallback")
		public Boolean getMultipleExchangeIndexAnnexFallback() {
			return multipleExchangeIndexAnnexFallback;
		}
		
		@Override
		@RosettaAttribute("componentSecurityIndexAnnexFallback")
		public Boolean getComponentSecurityIndexAnnexFallback() {
			return componentSecurityIndexAnnexFallback;
		}
		
		@Override
		@RosettaAttribute("localJurisdiction")
		public FieldWithMetaString.FieldWithMetaStringBuilder getLocalJurisdiction() {
			return localJurisdiction;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateLocalJurisdiction() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (localJurisdiction!=null) {
				result = localJurisdiction;
			}
			else {
				result = localJurisdiction = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("relevantJurisdiction")
		public FieldWithMetaString.FieldWithMetaStringBuilder getRelevantJurisdiction() {
			return relevantJurisdiction;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateRelevantJurisdiction() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (relevantJurisdiction!=null) {
				result = relevantJurisdiction;
			}
			else {
				result = relevantJurisdiction = FieldWithMetaString.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("multipleExchangeIndexAnnexFallback")
		public EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder setMultipleExchangeIndexAnnexFallback(Boolean multipleExchangeIndexAnnexFallback) {
			this.multipleExchangeIndexAnnexFallback = multipleExchangeIndexAnnexFallback==null?null:multipleExchangeIndexAnnexFallback;
			return this;
		}
		@Override
		@RosettaAttribute("componentSecurityIndexAnnexFallback")
		public EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder setComponentSecurityIndexAnnexFallback(Boolean componentSecurityIndexAnnexFallback) {
			this.componentSecurityIndexAnnexFallback = componentSecurityIndexAnnexFallback==null?null:componentSecurityIndexAnnexFallback;
			return this;
		}
		@Override
		@RosettaAttribute("localJurisdiction")
		public EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder setLocalJurisdiction(FieldWithMetaString localJurisdiction) {
			this.localJurisdiction = localJurisdiction==null?null:localJurisdiction.toBuilder();
			return this;
		}
		@Override
		public EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder setLocalJurisdictionValue(String localJurisdiction) {
			this.getOrCreateLocalJurisdiction().setValue(localJurisdiction);
			return this;
		}
		@Override
		@RosettaAttribute("relevantJurisdiction")
		public EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder setRelevantJurisdiction(FieldWithMetaString relevantJurisdiction) {
			this.relevantJurisdiction = relevantJurisdiction==null?null:relevantJurisdiction.toBuilder();
			return this;
		}
		@Override
		public EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder setRelevantJurisdictionValue(String relevantJurisdiction) {
			this.getOrCreateRelevantJurisdiction().setValue(relevantJurisdiction);
			return this;
		}
		
		@Override
		public EquityUnderlierProvisions build() {
			return new EquityUnderlierProvisions.EquityUnderlierProvisionsImpl(this);
		}
		
		@Override
		public EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder prune() {
			if (localJurisdiction!=null && !localJurisdiction.prune().hasData()) localJurisdiction = null;
			if (relevantJurisdiction!=null && !relevantJurisdiction.prune().hasData()) relevantJurisdiction = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getMultipleExchangeIndexAnnexFallback()!=null) return true;
			if (getComponentSecurityIndexAnnexFallback()!=null) return true;
			if (getLocalJurisdiction()!=null) return true;
			if (getRelevantJurisdiction()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder o = (EquityUnderlierProvisions.EquityUnderlierProvisionsBuilder) other;
			
			merger.mergeRosetta(getLocalJurisdiction(), o.getLocalJurisdiction(), this::setLocalJurisdiction);
			merger.mergeRosetta(getRelevantJurisdiction(), o.getRelevantJurisdiction(), this::setRelevantJurisdiction);
			
			merger.mergeBasic(getMultipleExchangeIndexAnnexFallback(), o.getMultipleExchangeIndexAnnexFallback(), this::setMultipleExchangeIndexAnnexFallback);
			merger.mergeBasic(getComponentSecurityIndexAnnexFallback(), o.getComponentSecurityIndexAnnexFallback(), this::setComponentSecurityIndexAnnexFallback);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			EquityUnderlierProvisions _that = getType().cast(o);
		
			if (!Objects.equals(multipleExchangeIndexAnnexFallback, _that.getMultipleExchangeIndexAnnexFallback())) return false;
			if (!Objects.equals(componentSecurityIndexAnnexFallback, _that.getComponentSecurityIndexAnnexFallback())) return false;
			if (!Objects.equals(localJurisdiction, _that.getLocalJurisdiction())) return false;
			if (!Objects.equals(relevantJurisdiction, _that.getRelevantJurisdiction())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (multipleExchangeIndexAnnexFallback != null ? multipleExchangeIndexAnnexFallback.hashCode() : 0);
			_result = 31 * _result + (componentSecurityIndexAnnexFallback != null ? componentSecurityIndexAnnexFallback.hashCode() : 0);
			_result = 31 * _result + (localJurisdiction != null ? localJurisdiction.hashCode() : 0);
			_result = 31 * _result + (relevantJurisdiction != null ? relevantJurisdiction.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EquityUnderlierProvisionsBuilder {" +
				"multipleExchangeIndexAnnexFallback=" + this.multipleExchangeIndexAnnexFallback + ", " +
				"componentSecurityIndexAnnexFallback=" + this.componentSecurityIndexAnnexFallback + ", " +
				"localJurisdiction=" + this.localJurisdiction + ", " +
				"relevantJurisdiction=" + this.relevantJurisdiction +
			'}';
		}
	}
}
