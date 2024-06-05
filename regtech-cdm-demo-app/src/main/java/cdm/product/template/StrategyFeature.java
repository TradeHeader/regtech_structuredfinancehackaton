package cdm.product.template;

import cdm.product.template.CalendarSpread;
import cdm.product.template.StrategyFeature;
import cdm.product.template.StrategyFeature.StrategyFeatureBuilder;
import cdm.product.template.StrategyFeature.StrategyFeatureBuilderImpl;
import cdm.product.template.StrategyFeature.StrategyFeatureImpl;
import cdm.product.template.StrikeSpread;
import cdm.product.template.meta.StrategyFeatureMeta;
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
 * A class for defining option strategy features.
 * @version ${project.version}
 */
@RosettaDataType(value="StrategyFeature", builder=StrategyFeature.StrategyFeatureBuilderImpl.class, version="${project.version}")
public interface StrategyFeature extends RosettaModelObject {

	StrategyFeatureMeta metaData = new StrategyFeatureMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Definition of the upper strike in a strike spread.
	 */
	StrikeSpread getStrikeSpread();
	/**
	 * Definition of the later expiration date in a calendar spread.
	 */
	CalendarSpread getCalendarSpread();

	/*********************** Build Methods  ***********************/
	StrategyFeature build();
	
	StrategyFeature.StrategyFeatureBuilder toBuilder();
	
	static StrategyFeature.StrategyFeatureBuilder builder() {
		return new StrategyFeature.StrategyFeatureBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends StrategyFeature> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends StrategyFeature> getType() {
		return StrategyFeature.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("strikeSpread"), processor, StrikeSpread.class, getStrikeSpread());
		processRosetta(path.newSubPath("calendarSpread"), processor, CalendarSpread.class, getCalendarSpread());
	}
	

	/*********************** Builder Interface  ***********************/
	interface StrategyFeatureBuilder extends StrategyFeature, RosettaModelObjectBuilder {
		StrikeSpread.StrikeSpreadBuilder getOrCreateStrikeSpread();
		StrikeSpread.StrikeSpreadBuilder getStrikeSpread();
		CalendarSpread.CalendarSpreadBuilder getOrCreateCalendarSpread();
		CalendarSpread.CalendarSpreadBuilder getCalendarSpread();
		StrategyFeature.StrategyFeatureBuilder setStrikeSpread(StrikeSpread strikeSpread);
		StrategyFeature.StrategyFeatureBuilder setCalendarSpread(CalendarSpread calendarSpread);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("strikeSpread"), processor, StrikeSpread.StrikeSpreadBuilder.class, getStrikeSpread());
			processRosetta(path.newSubPath("calendarSpread"), processor, CalendarSpread.CalendarSpreadBuilder.class, getCalendarSpread());
		}
		

		StrategyFeature.StrategyFeatureBuilder prune();
	}

	/*********************** Immutable Implementation of StrategyFeature  ***********************/
	class StrategyFeatureImpl implements StrategyFeature {
		private final StrikeSpread strikeSpread;
		private final CalendarSpread calendarSpread;
		
		protected StrategyFeatureImpl(StrategyFeature.StrategyFeatureBuilder builder) {
			this.strikeSpread = ofNullable(builder.getStrikeSpread()).map(f->f.build()).orElse(null);
			this.calendarSpread = ofNullable(builder.getCalendarSpread()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("strikeSpread")
		public StrikeSpread getStrikeSpread() {
			return strikeSpread;
		}
		
		@Override
		@RosettaAttribute("calendarSpread")
		public CalendarSpread getCalendarSpread() {
			return calendarSpread;
		}
		
		@Override
		public StrategyFeature build() {
			return this;
		}
		
		@Override
		public StrategyFeature.StrategyFeatureBuilder toBuilder() {
			StrategyFeature.StrategyFeatureBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(StrategyFeature.StrategyFeatureBuilder builder) {
			ofNullable(getStrikeSpread()).ifPresent(builder::setStrikeSpread);
			ofNullable(getCalendarSpread()).ifPresent(builder::setCalendarSpread);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			StrategyFeature _that = getType().cast(o);
		
			if (!Objects.equals(strikeSpread, _that.getStrikeSpread())) return false;
			if (!Objects.equals(calendarSpread, _that.getCalendarSpread())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (strikeSpread != null ? strikeSpread.hashCode() : 0);
			_result = 31 * _result + (calendarSpread != null ? calendarSpread.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "StrategyFeature {" +
				"strikeSpread=" + this.strikeSpread + ", " +
				"calendarSpread=" + this.calendarSpread +
			'}';
		}
	}

	/*********************** Builder Implementation of StrategyFeature  ***********************/
	class StrategyFeatureBuilderImpl implements StrategyFeature.StrategyFeatureBuilder {
	
		protected StrikeSpread.StrikeSpreadBuilder strikeSpread;
		protected CalendarSpread.CalendarSpreadBuilder calendarSpread;
	
		public StrategyFeatureBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("strikeSpread")
		public StrikeSpread.StrikeSpreadBuilder getStrikeSpread() {
			return strikeSpread;
		}
		
		@Override
		public StrikeSpread.StrikeSpreadBuilder getOrCreateStrikeSpread() {
			StrikeSpread.StrikeSpreadBuilder result;
			if (strikeSpread!=null) {
				result = strikeSpread;
			}
			else {
				result = strikeSpread = StrikeSpread.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("calendarSpread")
		public CalendarSpread.CalendarSpreadBuilder getCalendarSpread() {
			return calendarSpread;
		}
		
		@Override
		public CalendarSpread.CalendarSpreadBuilder getOrCreateCalendarSpread() {
			CalendarSpread.CalendarSpreadBuilder result;
			if (calendarSpread!=null) {
				result = calendarSpread;
			}
			else {
				result = calendarSpread = CalendarSpread.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("strikeSpread")
		public StrategyFeature.StrategyFeatureBuilder setStrikeSpread(StrikeSpread strikeSpread) {
			this.strikeSpread = strikeSpread==null?null:strikeSpread.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("calendarSpread")
		public StrategyFeature.StrategyFeatureBuilder setCalendarSpread(CalendarSpread calendarSpread) {
			this.calendarSpread = calendarSpread==null?null:calendarSpread.toBuilder();
			return this;
		}
		
		@Override
		public StrategyFeature build() {
			return new StrategyFeature.StrategyFeatureImpl(this);
		}
		
		@Override
		public StrategyFeature.StrategyFeatureBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public StrategyFeature.StrategyFeatureBuilder prune() {
			if (strikeSpread!=null && !strikeSpread.prune().hasData()) strikeSpread = null;
			if (calendarSpread!=null && !calendarSpread.prune().hasData()) calendarSpread = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getStrikeSpread()!=null && getStrikeSpread().hasData()) return true;
			if (getCalendarSpread()!=null && getCalendarSpread().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public StrategyFeature.StrategyFeatureBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			StrategyFeature.StrategyFeatureBuilder o = (StrategyFeature.StrategyFeatureBuilder) other;
			
			merger.mergeRosetta(getStrikeSpread(), o.getStrikeSpread(), this::setStrikeSpread);
			merger.mergeRosetta(getCalendarSpread(), o.getCalendarSpread(), this::setCalendarSpread);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			StrategyFeature _that = getType().cast(o);
		
			if (!Objects.equals(strikeSpread, _that.getStrikeSpread())) return false;
			if (!Objects.equals(calendarSpread, _that.getCalendarSpread())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (strikeSpread != null ? strikeSpread.hashCode() : 0);
			_result = 31 * _result + (calendarSpread != null ? calendarSpread.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "StrategyFeatureBuilder {" +
				"strikeSpread=" + this.strikeSpread + ", " +
				"calendarSpread=" + this.calendarSpread +
			'}';
		}
	}
}
