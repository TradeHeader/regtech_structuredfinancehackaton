package cdm.observable.asset;

import cdm.observable.asset.InformationProviderEnum;
import cdm.observable.asset.InformationSource;
import cdm.observable.asset.InformationSource.InformationSourceBuilder;
import cdm.observable.asset.InformationSource.InformationSourceBuilderImpl;
import cdm.observable.asset.InformationSource.InformationSourceImpl;
import cdm.observable.asset.meta.InformationSourceMeta;
import cdm.observable.asset.metafields.FieldWithMetaInformationProviderEnum;
import cdm.observable.asset.metafields.FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder;
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
 * A class defining the source for a piece of information (e.g. a rate fix or an FX fixing). The attribute names have been adjusted from FpML to address the fact that the information is not limited to rates.
 * @version ${project.version}
 */
@RosettaDataType(value="InformationSource", builder=InformationSource.InformationSourceBuilderImpl.class, version="${project.version}")
public interface InformationSource extends RosettaModelObject {

	InformationSourceMeta metaData = new InformationSourceMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * An information source for obtaining a market data point. For example Bloomberg, Reuters, Telerate, etc.
	 */
	FieldWithMetaInformationProviderEnum getSourceProvider();
	/**
	 * A specific page for the source for obtaining a market data point. In FpML, this is specified as a scheme, rateSourcePageScheme, for which no coding Scheme or URI is specified.
	 */
	FieldWithMetaString getSourcePage();
	/**
	 * The heading for the source on a given source page.
	 */
	String getSourcePageHeading();

	/*********************** Build Methods  ***********************/
	InformationSource build();
	
	InformationSource.InformationSourceBuilder toBuilder();
	
	static InformationSource.InformationSourceBuilder builder() {
		return new InformationSource.InformationSourceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends InformationSource> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends InformationSource> getType() {
		return InformationSource.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("sourceProvider"), processor, FieldWithMetaInformationProviderEnum.class, getSourceProvider());
		processRosetta(path.newSubPath("sourcePage"), processor, FieldWithMetaString.class, getSourcePage());
		processor.processBasic(path.newSubPath("sourcePageHeading"), String.class, getSourcePageHeading(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface InformationSourceBuilder extends InformationSource, RosettaModelObjectBuilder {
		FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder getOrCreateSourceProvider();
		FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder getSourceProvider();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateSourcePage();
		FieldWithMetaString.FieldWithMetaStringBuilder getSourcePage();
		InformationSource.InformationSourceBuilder setSourceProvider(FieldWithMetaInformationProviderEnum sourceProvider0);
		InformationSource.InformationSourceBuilder setSourceProviderValue(InformationProviderEnum sourceProvider1);
		InformationSource.InformationSourceBuilder setSourcePage(FieldWithMetaString sourcePage0);
		InformationSource.InformationSourceBuilder setSourcePageValue(String sourcePage1);
		InformationSource.InformationSourceBuilder setSourcePageHeading(String sourcePageHeading);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("sourceProvider"), processor, FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder.class, getSourceProvider());
			processRosetta(path.newSubPath("sourcePage"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getSourcePage());
			processor.processBasic(path.newSubPath("sourcePageHeading"), String.class, getSourcePageHeading(), this);
		}
		

		InformationSource.InformationSourceBuilder prune();
	}

	/*********************** Immutable Implementation of InformationSource  ***********************/
	class InformationSourceImpl implements InformationSource {
		private final FieldWithMetaInformationProviderEnum sourceProvider;
		private final FieldWithMetaString sourcePage;
		private final String sourcePageHeading;
		
		protected InformationSourceImpl(InformationSource.InformationSourceBuilder builder) {
			this.sourceProvider = ofNullable(builder.getSourceProvider()).map(f->f.build()).orElse(null);
			this.sourcePage = ofNullable(builder.getSourcePage()).map(f->f.build()).orElse(null);
			this.sourcePageHeading = builder.getSourcePageHeading();
		}
		
		@Override
		@RosettaAttribute("sourceProvider")
		public FieldWithMetaInformationProviderEnum getSourceProvider() {
			return sourceProvider;
		}
		
		@Override
		@RosettaAttribute("sourcePage")
		public FieldWithMetaString getSourcePage() {
			return sourcePage;
		}
		
		@Override
		@RosettaAttribute("sourcePageHeading")
		public String getSourcePageHeading() {
			return sourcePageHeading;
		}
		
		@Override
		public InformationSource build() {
			return this;
		}
		
		@Override
		public InformationSource.InformationSourceBuilder toBuilder() {
			InformationSource.InformationSourceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(InformationSource.InformationSourceBuilder builder) {
			ofNullable(getSourceProvider()).ifPresent(builder::setSourceProvider);
			ofNullable(getSourcePage()).ifPresent(builder::setSourcePage);
			ofNullable(getSourcePageHeading()).ifPresent(builder::setSourcePageHeading);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			InformationSource _that = getType().cast(o);
		
			if (!Objects.equals(sourceProvider, _that.getSourceProvider())) return false;
			if (!Objects.equals(sourcePage, _that.getSourcePage())) return false;
			if (!Objects.equals(sourcePageHeading, _that.getSourcePageHeading())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (sourceProvider != null ? sourceProvider.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (sourcePage != null ? sourcePage.hashCode() : 0);
			_result = 31 * _result + (sourcePageHeading != null ? sourcePageHeading.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InformationSource {" +
				"sourceProvider=" + this.sourceProvider + ", " +
				"sourcePage=" + this.sourcePage + ", " +
				"sourcePageHeading=" + this.sourcePageHeading +
			'}';
		}
	}

	/*********************** Builder Implementation of InformationSource  ***********************/
	class InformationSourceBuilderImpl implements InformationSource.InformationSourceBuilder {
	
		protected FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder sourceProvider;
		protected FieldWithMetaString.FieldWithMetaStringBuilder sourcePage;
		protected String sourcePageHeading;
	
		public InformationSourceBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("sourceProvider")
		public FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder getSourceProvider() {
			return sourceProvider;
		}
		
		@Override
		public FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder getOrCreateSourceProvider() {
			FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder result;
			if (sourceProvider!=null) {
				result = sourceProvider;
			}
			else {
				result = sourceProvider = FieldWithMetaInformationProviderEnum.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("sourcePage")
		public FieldWithMetaString.FieldWithMetaStringBuilder getSourcePage() {
			return sourcePage;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateSourcePage() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (sourcePage!=null) {
				result = sourcePage;
			}
			else {
				result = sourcePage = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("sourcePageHeading")
		public String getSourcePageHeading() {
			return sourcePageHeading;
		}
		
	
		@Override
		@RosettaAttribute("sourceProvider")
		public InformationSource.InformationSourceBuilder setSourceProvider(FieldWithMetaInformationProviderEnum sourceProvider) {
			this.sourceProvider = sourceProvider==null?null:sourceProvider.toBuilder();
			return this;
		}
		@Override
		public InformationSource.InformationSourceBuilder setSourceProviderValue(InformationProviderEnum sourceProvider) {
			this.getOrCreateSourceProvider().setValue(sourceProvider);
			return this;
		}
		@Override
		@RosettaAttribute("sourcePage")
		public InformationSource.InformationSourceBuilder setSourcePage(FieldWithMetaString sourcePage) {
			this.sourcePage = sourcePage==null?null:sourcePage.toBuilder();
			return this;
		}
		@Override
		public InformationSource.InformationSourceBuilder setSourcePageValue(String sourcePage) {
			this.getOrCreateSourcePage().setValue(sourcePage);
			return this;
		}
		@Override
		@RosettaAttribute("sourcePageHeading")
		public InformationSource.InformationSourceBuilder setSourcePageHeading(String sourcePageHeading) {
			this.sourcePageHeading = sourcePageHeading==null?null:sourcePageHeading;
			return this;
		}
		
		@Override
		public InformationSource build() {
			return new InformationSource.InformationSourceImpl(this);
		}
		
		@Override
		public InformationSource.InformationSourceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InformationSource.InformationSourceBuilder prune() {
			if (sourceProvider!=null && !sourceProvider.prune().hasData()) sourceProvider = null;
			if (sourcePage!=null && !sourcePage.prune().hasData()) sourcePage = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSourceProvider()!=null) return true;
			if (getSourcePage()!=null) return true;
			if (getSourcePageHeading()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InformationSource.InformationSourceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			InformationSource.InformationSourceBuilder o = (InformationSource.InformationSourceBuilder) other;
			
			merger.mergeRosetta(getSourceProvider(), o.getSourceProvider(), this::setSourceProvider);
			merger.mergeRosetta(getSourcePage(), o.getSourcePage(), this::setSourcePage);
			
			merger.mergeBasic(getSourcePageHeading(), o.getSourcePageHeading(), this::setSourcePageHeading);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			InformationSource _that = getType().cast(o);
		
			if (!Objects.equals(sourceProvider, _that.getSourceProvider())) return false;
			if (!Objects.equals(sourcePage, _that.getSourcePage())) return false;
			if (!Objects.equals(sourcePageHeading, _that.getSourcePageHeading())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (sourceProvider != null ? sourceProvider.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (sourcePage != null ? sourcePage.hashCode() : 0);
			_result = 31 * _result + (sourcePageHeading != null ? sourcePageHeading.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InformationSourceBuilder {" +
				"sourceProvider=" + this.sourceProvider + ", " +
				"sourcePage=" + this.sourcePage + ", " +
				"sourcePageHeading=" + this.sourcePageHeading +
			'}';
		}
	}
}
