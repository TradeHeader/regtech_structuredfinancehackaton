package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.CollateralTaxonomy;
import cdm.base.staticdata.asset.common.CollateralTaxonomy.CollateralTaxonomyBuilder;
import cdm.base.staticdata.asset.common.CollateralTaxonomy.CollateralTaxonomyBuilderImpl;
import cdm.base.staticdata.asset.common.CollateralTaxonomy.CollateralTaxonomyImpl;
import cdm.base.staticdata.asset.common.CollateralTaxonomyValue;
import cdm.base.staticdata.asset.common.TaxonomySourceEnum;
import cdm.base.staticdata.asset.common.meta.CollateralTaxonomyMeta;
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
 * Specifies the collateral taxonomy, which is composed of a taxonomy value and a taxonomy source.
 * @version ${project.version}
 */
@RosettaDataType(value="CollateralTaxonomy", builder=CollateralTaxonomy.CollateralTaxonomyBuilderImpl.class, version="${project.version}")
public interface CollateralTaxonomy extends RosettaModelObject {

	CollateralTaxonomyMeta metaData = new CollateralTaxonomyMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the taxonomy value.
	 */
	CollateralTaxonomyValue getTaxonomyValue();
	/**
	 * Specifies the taxonomy source.
	 */
	TaxonomySourceEnum getTaxonomySource();

	/*********************** Build Methods  ***********************/
	CollateralTaxonomy build();
	
	CollateralTaxonomy.CollateralTaxonomyBuilder toBuilder();
	
	static CollateralTaxonomy.CollateralTaxonomyBuilder builder() {
		return new CollateralTaxonomy.CollateralTaxonomyBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CollateralTaxonomy> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CollateralTaxonomy> getType() {
		return CollateralTaxonomy.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("taxonomyValue"), processor, CollateralTaxonomyValue.class, getTaxonomyValue());
		processor.processBasic(path.newSubPath("taxonomySource"), TaxonomySourceEnum.class, getTaxonomySource(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CollateralTaxonomyBuilder extends CollateralTaxonomy, RosettaModelObjectBuilder {
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder getOrCreateTaxonomyValue();
		CollateralTaxonomyValue.CollateralTaxonomyValueBuilder getTaxonomyValue();
		CollateralTaxonomy.CollateralTaxonomyBuilder setTaxonomyValue(CollateralTaxonomyValue taxonomyValue);
		CollateralTaxonomy.CollateralTaxonomyBuilder setTaxonomySource(TaxonomySourceEnum taxonomySource);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("taxonomyValue"), processor, CollateralTaxonomyValue.CollateralTaxonomyValueBuilder.class, getTaxonomyValue());
			processor.processBasic(path.newSubPath("taxonomySource"), TaxonomySourceEnum.class, getTaxonomySource(), this);
		}
		

		CollateralTaxonomy.CollateralTaxonomyBuilder prune();
	}

	/*********************** Immutable Implementation of CollateralTaxonomy  ***********************/
	class CollateralTaxonomyImpl implements CollateralTaxonomy {
		private final CollateralTaxonomyValue taxonomyValue;
		private final TaxonomySourceEnum taxonomySource;
		
		protected CollateralTaxonomyImpl(CollateralTaxonomy.CollateralTaxonomyBuilder builder) {
			this.taxonomyValue = ofNullable(builder.getTaxonomyValue()).map(f->f.build()).orElse(null);
			this.taxonomySource = builder.getTaxonomySource();
		}
		
		@Override
		@RosettaAttribute("taxonomyValue")
		public CollateralTaxonomyValue getTaxonomyValue() {
			return taxonomyValue;
		}
		
		@Override
		@RosettaAttribute("taxonomySource")
		public TaxonomySourceEnum getTaxonomySource() {
			return taxonomySource;
		}
		
		@Override
		public CollateralTaxonomy build() {
			return this;
		}
		
		@Override
		public CollateralTaxonomy.CollateralTaxonomyBuilder toBuilder() {
			CollateralTaxonomy.CollateralTaxonomyBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CollateralTaxonomy.CollateralTaxonomyBuilder builder) {
			ofNullable(getTaxonomyValue()).ifPresent(builder::setTaxonomyValue);
			ofNullable(getTaxonomySource()).ifPresent(builder::setTaxonomySource);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralTaxonomy _that = getType().cast(o);
		
			if (!Objects.equals(taxonomyValue, _that.getTaxonomyValue())) return false;
			if (!Objects.equals(taxonomySource, _that.getTaxonomySource())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (taxonomyValue != null ? taxonomyValue.hashCode() : 0);
			_result = 31 * _result + (taxonomySource != null ? taxonomySource.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralTaxonomy {" +
				"taxonomyValue=" + this.taxonomyValue + ", " +
				"taxonomySource=" + this.taxonomySource +
			'}';
		}
	}

	/*********************** Builder Implementation of CollateralTaxonomy  ***********************/
	class CollateralTaxonomyBuilderImpl implements CollateralTaxonomy.CollateralTaxonomyBuilder {
	
		protected CollateralTaxonomyValue.CollateralTaxonomyValueBuilder taxonomyValue;
		protected TaxonomySourceEnum taxonomySource;
	
		public CollateralTaxonomyBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("taxonomyValue")
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder getTaxonomyValue() {
			return taxonomyValue;
		}
		
		@Override
		public CollateralTaxonomyValue.CollateralTaxonomyValueBuilder getOrCreateTaxonomyValue() {
			CollateralTaxonomyValue.CollateralTaxonomyValueBuilder result;
			if (taxonomyValue!=null) {
				result = taxonomyValue;
			}
			else {
				result = taxonomyValue = CollateralTaxonomyValue.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("taxonomySource")
		public TaxonomySourceEnum getTaxonomySource() {
			return taxonomySource;
		}
		
	
		@Override
		@RosettaAttribute("taxonomyValue")
		public CollateralTaxonomy.CollateralTaxonomyBuilder setTaxonomyValue(CollateralTaxonomyValue taxonomyValue) {
			this.taxonomyValue = taxonomyValue==null?null:taxonomyValue.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("taxonomySource")
		public CollateralTaxonomy.CollateralTaxonomyBuilder setTaxonomySource(TaxonomySourceEnum taxonomySource) {
			this.taxonomySource = taxonomySource==null?null:taxonomySource;
			return this;
		}
		
		@Override
		public CollateralTaxonomy build() {
			return new CollateralTaxonomy.CollateralTaxonomyImpl(this);
		}
		
		@Override
		public CollateralTaxonomy.CollateralTaxonomyBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralTaxonomy.CollateralTaxonomyBuilder prune() {
			if (taxonomyValue!=null && !taxonomyValue.prune().hasData()) taxonomyValue = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getTaxonomyValue()!=null && getTaxonomyValue().hasData()) return true;
			if (getTaxonomySource()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralTaxonomy.CollateralTaxonomyBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CollateralTaxonomy.CollateralTaxonomyBuilder o = (CollateralTaxonomy.CollateralTaxonomyBuilder) other;
			
			merger.mergeRosetta(getTaxonomyValue(), o.getTaxonomyValue(), this::setTaxonomyValue);
			
			merger.mergeBasic(getTaxonomySource(), o.getTaxonomySource(), this::setTaxonomySource);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralTaxonomy _that = getType().cast(o);
		
			if (!Objects.equals(taxonomyValue, _that.getTaxonomyValue())) return false;
			if (!Objects.equals(taxonomySource, _that.getTaxonomySource())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (taxonomyValue != null ? taxonomyValue.hashCode() : 0);
			_result = 31 * _result + (taxonomySource != null ? taxonomySource.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralTaxonomyBuilder {" +
				"taxonomyValue=" + this.taxonomyValue + ", " +
				"taxonomySource=" + this.taxonomySource +
			'}';
		}
	}
}
