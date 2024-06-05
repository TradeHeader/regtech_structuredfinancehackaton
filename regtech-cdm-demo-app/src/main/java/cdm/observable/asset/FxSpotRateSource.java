package cdm.observable.asset;

import cdm.observable.asset.FxSpotRateSource;
import cdm.observable.asset.FxSpotRateSource.FxSpotRateSourceBuilder;
import cdm.observable.asset.FxSpotRateSource.FxSpotRateSourceBuilderImpl;
import cdm.observable.asset.FxSpotRateSource.FxSpotRateSourceImpl;
import cdm.observable.asset.InformationSource;
import cdm.observable.asset.meta.FxSpotRateSourceMeta;
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
 * A class defining the rate source and fixing time for an FX rate.
 * @version ${project.version}
 */
@RosettaDataType(value="FxSpotRateSource", builder=FxSpotRateSource.FxSpotRateSourceBuilderImpl.class, version="${project.version}")
public interface FxSpotRateSource extends RosettaModelObject {

	FxSpotRateSourceMeta metaData = new FxSpotRateSourceMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The primary source for where the rate observation will occur. Will typically be either a page or a reference bank published rate.
	 */
	InformationSource getPrimarySource();
	/**
	 * An alternative, or secondary, source for where the rate observation will occur. Will typically be either a page or a reference bank published rate.
	 */
	InformationSource getSecondarySource();

	/*********************** Build Methods  ***********************/
	FxSpotRateSource build();
	
	FxSpotRateSource.FxSpotRateSourceBuilder toBuilder();
	
	static FxSpotRateSource.FxSpotRateSourceBuilder builder() {
		return new FxSpotRateSource.FxSpotRateSourceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FxSpotRateSource> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FxSpotRateSource> getType() {
		return FxSpotRateSource.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("primarySource"), processor, InformationSource.class, getPrimarySource());
		processRosetta(path.newSubPath("secondarySource"), processor, InformationSource.class, getSecondarySource());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FxSpotRateSourceBuilder extends FxSpotRateSource, RosettaModelObjectBuilder {
		InformationSource.InformationSourceBuilder getOrCreatePrimarySource();
		InformationSource.InformationSourceBuilder getPrimarySource();
		InformationSource.InformationSourceBuilder getOrCreateSecondarySource();
		InformationSource.InformationSourceBuilder getSecondarySource();
		FxSpotRateSource.FxSpotRateSourceBuilder setPrimarySource(InformationSource primarySource);
		FxSpotRateSource.FxSpotRateSourceBuilder setSecondarySource(InformationSource secondarySource);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("primarySource"), processor, InformationSource.InformationSourceBuilder.class, getPrimarySource());
			processRosetta(path.newSubPath("secondarySource"), processor, InformationSource.InformationSourceBuilder.class, getSecondarySource());
		}
		

		FxSpotRateSource.FxSpotRateSourceBuilder prune();
	}

	/*********************** Immutable Implementation of FxSpotRateSource  ***********************/
	class FxSpotRateSourceImpl implements FxSpotRateSource {
		private final InformationSource primarySource;
		private final InformationSource secondarySource;
		
		protected FxSpotRateSourceImpl(FxSpotRateSource.FxSpotRateSourceBuilder builder) {
			this.primarySource = ofNullable(builder.getPrimarySource()).map(f->f.build()).orElse(null);
			this.secondarySource = ofNullable(builder.getSecondarySource()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("primarySource")
		public InformationSource getPrimarySource() {
			return primarySource;
		}
		
		@Override
		@RosettaAttribute("secondarySource")
		public InformationSource getSecondarySource() {
			return secondarySource;
		}
		
		@Override
		public FxSpotRateSource build() {
			return this;
		}
		
		@Override
		public FxSpotRateSource.FxSpotRateSourceBuilder toBuilder() {
			FxSpotRateSource.FxSpotRateSourceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FxSpotRateSource.FxSpotRateSourceBuilder builder) {
			ofNullable(getPrimarySource()).ifPresent(builder::setPrimarySource);
			ofNullable(getSecondarySource()).ifPresent(builder::setSecondarySource);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FxSpotRateSource _that = getType().cast(o);
		
			if (!Objects.equals(primarySource, _that.getPrimarySource())) return false;
			if (!Objects.equals(secondarySource, _that.getSecondarySource())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (primarySource != null ? primarySource.hashCode() : 0);
			_result = 31 * _result + (secondarySource != null ? secondarySource.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FxSpotRateSource {" +
				"primarySource=" + this.primarySource + ", " +
				"secondarySource=" + this.secondarySource +
			'}';
		}
	}

	/*********************** Builder Implementation of FxSpotRateSource  ***********************/
	class FxSpotRateSourceBuilderImpl implements FxSpotRateSource.FxSpotRateSourceBuilder {
	
		protected InformationSource.InformationSourceBuilder primarySource;
		protected InformationSource.InformationSourceBuilder secondarySource;
	
		public FxSpotRateSourceBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("primarySource")
		public InformationSource.InformationSourceBuilder getPrimarySource() {
			return primarySource;
		}
		
		@Override
		public InformationSource.InformationSourceBuilder getOrCreatePrimarySource() {
			InformationSource.InformationSourceBuilder result;
			if (primarySource!=null) {
				result = primarySource;
			}
			else {
				result = primarySource = InformationSource.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("secondarySource")
		public InformationSource.InformationSourceBuilder getSecondarySource() {
			return secondarySource;
		}
		
		@Override
		public InformationSource.InformationSourceBuilder getOrCreateSecondarySource() {
			InformationSource.InformationSourceBuilder result;
			if (secondarySource!=null) {
				result = secondarySource;
			}
			else {
				result = secondarySource = InformationSource.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("primarySource")
		public FxSpotRateSource.FxSpotRateSourceBuilder setPrimarySource(InformationSource primarySource) {
			this.primarySource = primarySource==null?null:primarySource.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("secondarySource")
		public FxSpotRateSource.FxSpotRateSourceBuilder setSecondarySource(InformationSource secondarySource) {
			this.secondarySource = secondarySource==null?null:secondarySource.toBuilder();
			return this;
		}
		
		@Override
		public FxSpotRateSource build() {
			return new FxSpotRateSource.FxSpotRateSourceImpl(this);
		}
		
		@Override
		public FxSpotRateSource.FxSpotRateSourceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FxSpotRateSource.FxSpotRateSourceBuilder prune() {
			if (primarySource!=null && !primarySource.prune().hasData()) primarySource = null;
			if (secondarySource!=null && !secondarySource.prune().hasData()) secondarySource = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPrimarySource()!=null && getPrimarySource().hasData()) return true;
			if (getSecondarySource()!=null && getSecondarySource().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FxSpotRateSource.FxSpotRateSourceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FxSpotRateSource.FxSpotRateSourceBuilder o = (FxSpotRateSource.FxSpotRateSourceBuilder) other;
			
			merger.mergeRosetta(getPrimarySource(), o.getPrimarySource(), this::setPrimarySource);
			merger.mergeRosetta(getSecondarySource(), o.getSecondarySource(), this::setSecondarySource);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FxSpotRateSource _that = getType().cast(o);
		
			if (!Objects.equals(primarySource, _that.getPrimarySource())) return false;
			if (!Objects.equals(secondarySource, _that.getSecondarySource())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (primarySource != null ? primarySource.hashCode() : 0);
			_result = 31 * _result + (secondarySource != null ? secondarySource.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FxSpotRateSourceBuilder {" +
				"primarySource=" + this.primarySource + ", " +
				"secondarySource=" + this.secondarySource +
			'}';
		}
	}
}
