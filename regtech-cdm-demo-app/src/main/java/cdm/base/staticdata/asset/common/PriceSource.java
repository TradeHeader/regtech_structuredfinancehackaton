package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.PriceSource;
import cdm.base.staticdata.asset.common.PriceSource.PriceSourceBuilder;
import cdm.base.staticdata.asset.common.PriceSource.PriceSourceBuilderImpl;
import cdm.base.staticdata.asset.common.PriceSource.PriceSourceImpl;
import cdm.base.staticdata.asset.common.meta.PriceSourceMeta;
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
import java.time.LocalTime;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Specifies a publication that provides the commodity price, including, where applicable, the details of where in the publication the price is published.
 * @version ${project.version}
 */
@RosettaDataType(value="PriceSource", builder=PriceSource.PriceSourceBuilderImpl.class, version="${project.version}")
public interface PriceSource extends RosettaModelObject {

	PriceSourceMeta metaData = new PriceSourceMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Defines a publication in which the price can be found. (e.g Gas Daily, Platts Bloomberg. Commodity publishers can be found at this URL:  http://www.fpml.org/coding-scheme/commodity-information-provider&gt;
	 */
	FieldWithMetaString getPricePublisher();
	/**
	 * Specifies the location of the price which may be a specific page, electornic screen name, or a code (e.g. a RIC code) where the price can be found.
	 */
	String getPriceSourceLocation();
	/**
	 * Specifies the heading or field name for the price  on a given page or screen, where applicable.
	 */
	String getPriceSourceHeading();
	/**
	 * Specifies the time at which the price should be observed.
	 */
	LocalTime getPriceSourceTime();

	/*********************** Build Methods  ***********************/
	PriceSource build();
	
	PriceSource.PriceSourceBuilder toBuilder();
	
	static PriceSource.PriceSourceBuilder builder() {
		return new PriceSource.PriceSourceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PriceSource> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PriceSource> getType() {
		return PriceSource.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("pricePublisher"), processor, FieldWithMetaString.class, getPricePublisher());
		processor.processBasic(path.newSubPath("priceSourceLocation"), String.class, getPriceSourceLocation(), this);
		processor.processBasic(path.newSubPath("priceSourceHeading"), String.class, getPriceSourceHeading(), this);
		processor.processBasic(path.newSubPath("priceSourceTime"), LocalTime.class, getPriceSourceTime(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface PriceSourceBuilder extends PriceSource, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreatePricePublisher();
		FieldWithMetaString.FieldWithMetaStringBuilder getPricePublisher();
		PriceSource.PriceSourceBuilder setPricePublisher(FieldWithMetaString pricePublisher0);
		PriceSource.PriceSourceBuilder setPricePublisherValue(String pricePublisher1);
		PriceSource.PriceSourceBuilder setPriceSourceLocation(String priceSourceLocation);
		PriceSource.PriceSourceBuilder setPriceSourceHeading(String priceSourceHeading);
		PriceSource.PriceSourceBuilder setPriceSourceTime(LocalTime priceSourceTime);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("pricePublisher"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getPricePublisher());
			processor.processBasic(path.newSubPath("priceSourceLocation"), String.class, getPriceSourceLocation(), this);
			processor.processBasic(path.newSubPath("priceSourceHeading"), String.class, getPriceSourceHeading(), this);
			processor.processBasic(path.newSubPath("priceSourceTime"), LocalTime.class, getPriceSourceTime(), this);
		}
		

		PriceSource.PriceSourceBuilder prune();
	}

	/*********************** Immutable Implementation of PriceSource  ***********************/
	class PriceSourceImpl implements PriceSource {
		private final FieldWithMetaString pricePublisher;
		private final String priceSourceLocation;
		private final String priceSourceHeading;
		private final LocalTime priceSourceTime;
		
		protected PriceSourceImpl(PriceSource.PriceSourceBuilder builder) {
			this.pricePublisher = ofNullable(builder.getPricePublisher()).map(f->f.build()).orElse(null);
			this.priceSourceLocation = builder.getPriceSourceLocation();
			this.priceSourceHeading = builder.getPriceSourceHeading();
			this.priceSourceTime = builder.getPriceSourceTime();
		}
		
		@Override
		@RosettaAttribute("pricePublisher")
		public FieldWithMetaString getPricePublisher() {
			return pricePublisher;
		}
		
		@Override
		@RosettaAttribute("priceSourceLocation")
		public String getPriceSourceLocation() {
			return priceSourceLocation;
		}
		
		@Override
		@RosettaAttribute("priceSourceHeading")
		public String getPriceSourceHeading() {
			return priceSourceHeading;
		}
		
		@Override
		@RosettaAttribute("priceSourceTime")
		public LocalTime getPriceSourceTime() {
			return priceSourceTime;
		}
		
		@Override
		public PriceSource build() {
			return this;
		}
		
		@Override
		public PriceSource.PriceSourceBuilder toBuilder() {
			PriceSource.PriceSourceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PriceSource.PriceSourceBuilder builder) {
			ofNullable(getPricePublisher()).ifPresent(builder::setPricePublisher);
			ofNullable(getPriceSourceLocation()).ifPresent(builder::setPriceSourceLocation);
			ofNullable(getPriceSourceHeading()).ifPresent(builder::setPriceSourceHeading);
			ofNullable(getPriceSourceTime()).ifPresent(builder::setPriceSourceTime);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PriceSource _that = getType().cast(o);
		
			if (!Objects.equals(pricePublisher, _that.getPricePublisher())) return false;
			if (!Objects.equals(priceSourceLocation, _that.getPriceSourceLocation())) return false;
			if (!Objects.equals(priceSourceHeading, _that.getPriceSourceHeading())) return false;
			if (!Objects.equals(priceSourceTime, _that.getPriceSourceTime())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (pricePublisher != null ? pricePublisher.hashCode() : 0);
			_result = 31 * _result + (priceSourceLocation != null ? priceSourceLocation.hashCode() : 0);
			_result = 31 * _result + (priceSourceHeading != null ? priceSourceHeading.hashCode() : 0);
			_result = 31 * _result + (priceSourceTime != null ? priceSourceTime.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PriceSource {" +
				"pricePublisher=" + this.pricePublisher + ", " +
				"priceSourceLocation=" + this.priceSourceLocation + ", " +
				"priceSourceHeading=" + this.priceSourceHeading + ", " +
				"priceSourceTime=" + this.priceSourceTime +
			'}';
		}
	}

	/*********************** Builder Implementation of PriceSource  ***********************/
	class PriceSourceBuilderImpl implements PriceSource.PriceSourceBuilder {
	
		protected FieldWithMetaString.FieldWithMetaStringBuilder pricePublisher;
		protected String priceSourceLocation;
		protected String priceSourceHeading;
		protected LocalTime priceSourceTime;
	
		public PriceSourceBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("pricePublisher")
		public FieldWithMetaString.FieldWithMetaStringBuilder getPricePublisher() {
			return pricePublisher;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreatePricePublisher() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (pricePublisher!=null) {
				result = pricePublisher;
			}
			else {
				result = pricePublisher = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("priceSourceLocation")
		public String getPriceSourceLocation() {
			return priceSourceLocation;
		}
		
		@Override
		@RosettaAttribute("priceSourceHeading")
		public String getPriceSourceHeading() {
			return priceSourceHeading;
		}
		
		@Override
		@RosettaAttribute("priceSourceTime")
		public LocalTime getPriceSourceTime() {
			return priceSourceTime;
		}
		
	
		@Override
		@RosettaAttribute("pricePublisher")
		public PriceSource.PriceSourceBuilder setPricePublisher(FieldWithMetaString pricePublisher) {
			this.pricePublisher = pricePublisher==null?null:pricePublisher.toBuilder();
			return this;
		}
		@Override
		public PriceSource.PriceSourceBuilder setPricePublisherValue(String pricePublisher) {
			this.getOrCreatePricePublisher().setValue(pricePublisher);
			return this;
		}
		@Override
		@RosettaAttribute("priceSourceLocation")
		public PriceSource.PriceSourceBuilder setPriceSourceLocation(String priceSourceLocation) {
			this.priceSourceLocation = priceSourceLocation==null?null:priceSourceLocation;
			return this;
		}
		@Override
		@RosettaAttribute("priceSourceHeading")
		public PriceSource.PriceSourceBuilder setPriceSourceHeading(String priceSourceHeading) {
			this.priceSourceHeading = priceSourceHeading==null?null:priceSourceHeading;
			return this;
		}
		@Override
		@RosettaAttribute("priceSourceTime")
		public PriceSource.PriceSourceBuilder setPriceSourceTime(LocalTime priceSourceTime) {
			this.priceSourceTime = priceSourceTime==null?null:priceSourceTime;
			return this;
		}
		
		@Override
		public PriceSource build() {
			return new PriceSource.PriceSourceImpl(this);
		}
		
		@Override
		public PriceSource.PriceSourceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PriceSource.PriceSourceBuilder prune() {
			if (pricePublisher!=null && !pricePublisher.prune().hasData()) pricePublisher = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPricePublisher()!=null) return true;
			if (getPriceSourceLocation()!=null) return true;
			if (getPriceSourceHeading()!=null) return true;
			if (getPriceSourceTime()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PriceSource.PriceSourceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PriceSource.PriceSourceBuilder o = (PriceSource.PriceSourceBuilder) other;
			
			merger.mergeRosetta(getPricePublisher(), o.getPricePublisher(), this::setPricePublisher);
			
			merger.mergeBasic(getPriceSourceLocation(), o.getPriceSourceLocation(), this::setPriceSourceLocation);
			merger.mergeBasic(getPriceSourceHeading(), o.getPriceSourceHeading(), this::setPriceSourceHeading);
			merger.mergeBasic(getPriceSourceTime(), o.getPriceSourceTime(), this::setPriceSourceTime);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PriceSource _that = getType().cast(o);
		
			if (!Objects.equals(pricePublisher, _that.getPricePublisher())) return false;
			if (!Objects.equals(priceSourceLocation, _that.getPriceSourceLocation())) return false;
			if (!Objects.equals(priceSourceHeading, _that.getPriceSourceHeading())) return false;
			if (!Objects.equals(priceSourceTime, _that.getPriceSourceTime())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (pricePublisher != null ? pricePublisher.hashCode() : 0);
			_result = 31 * _result + (priceSourceLocation != null ? priceSourceLocation.hashCode() : 0);
			_result = 31 * _result + (priceSourceHeading != null ? priceSourceHeading.hashCode() : 0);
			_result = 31 * _result + (priceSourceTime != null ? priceSourceTime.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PriceSourceBuilder {" +
				"pricePublisher=" + this.pricePublisher + ", " +
				"priceSourceLocation=" + this.priceSourceLocation + ", " +
				"priceSourceHeading=" + this.priceSourceHeading + ", " +
				"priceSourceTime=" + this.priceSourceTime +
			'}';
		}
	}
}
