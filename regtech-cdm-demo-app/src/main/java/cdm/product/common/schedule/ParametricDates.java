package cdm.product.common.schedule;

import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.DayOfWeekEnum;
import cdm.base.datetime.DayTypeEnum;
import cdm.product.asset.DayDistributionEnum;
import cdm.product.common.schedule.Lag;
import cdm.product.common.schedule.ParametricDates;
import cdm.product.common.schedule.ParametricDates.ParametricDatesBuilder;
import cdm.product.common.schedule.ParametricDates.ParametricDatesBuilderImpl;
import cdm.product.common.schedule.ParametricDates.ParametricDatesImpl;
import cdm.product.common.schedule.meta.ParametricDatesMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Defines rules for the dates on which the price will be determined.
 * @version ${project.version}
 */
@RosettaDataType(value="ParametricDates", builder=ParametricDates.ParametricDatesBuilderImpl.class, version="${project.version}")
public interface ParametricDates extends RosettaModelObject {

	ParametricDatesMeta metaData = new ParametricDatesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Denotes the enumerated values to specify the day type classification used in counting the number of days between two dates.
	 */
	DayTypeEnum getDayType();
	/**
	 * Denotes the method by which the pricing days are distributed across the pricing period.
	 */
	DayDistributionEnum getDayDistribution();
	/**
	 * Indicates the days of the week on which the price will be determined.
	 */
	List<DayOfWeekEnum> getDayOfWeek();
	/**
	 * Defines the occurrence of the dayOfWeek within the pricing period on which pricing will take place, e.g. the 3rd Friday within each Calculation Period. If omitted, every dayOfWeek will be a pricing day.
	 */
	BigDecimal getDayFrequency();
	/**
	 * The pricing period per calculation period if the pricing days do not wholly fall within the respective calculation period.
	 */
	Lag getLag();
	/**
	 * The enumerated values to specify the business centers.
	 */
	BusinessCenters getBusinessCenters();

	/*********************** Build Methods  ***********************/
	ParametricDates build();
	
	ParametricDates.ParametricDatesBuilder toBuilder();
	
	static ParametricDates.ParametricDatesBuilder builder() {
		return new ParametricDates.ParametricDatesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ParametricDates> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ParametricDates> getType() {
		return ParametricDates.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("dayType"), DayTypeEnum.class, getDayType(), this);
		processor.processBasic(path.newSubPath("dayDistribution"), DayDistributionEnum.class, getDayDistribution(), this);
		processor.processBasic(path.newSubPath("dayOfWeek"), DayOfWeekEnum.class, getDayOfWeek(), this);
		processor.processBasic(path.newSubPath("dayFrequency"), BigDecimal.class, getDayFrequency(), this);
		processRosetta(path.newSubPath("lag"), processor, Lag.class, getLag());
		processRosetta(path.newSubPath("businessCenters"), processor, BusinessCenters.class, getBusinessCenters());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ParametricDatesBuilder extends ParametricDates, RosettaModelObjectBuilder {
		Lag.LagBuilder getOrCreateLag();
		Lag.LagBuilder getLag();
		BusinessCenters.BusinessCentersBuilder getOrCreateBusinessCenters();
		BusinessCenters.BusinessCentersBuilder getBusinessCenters();
		ParametricDates.ParametricDatesBuilder setDayType(DayTypeEnum dayType);
		ParametricDates.ParametricDatesBuilder setDayDistribution(DayDistributionEnum dayDistribution);
		ParametricDates.ParametricDatesBuilder addDayOfWeek(DayOfWeekEnum dayOfWeek0);
		ParametricDates.ParametricDatesBuilder addDayOfWeek(DayOfWeekEnum dayOfWeek1, int _idx);
		ParametricDates.ParametricDatesBuilder addDayOfWeek(List<? extends DayOfWeekEnum> dayOfWeek2);
		ParametricDates.ParametricDatesBuilder setDayOfWeek(List<? extends DayOfWeekEnum> dayOfWeek3);
		ParametricDates.ParametricDatesBuilder setDayFrequency(BigDecimal dayFrequency);
		ParametricDates.ParametricDatesBuilder setLag(Lag lag);
		ParametricDates.ParametricDatesBuilder setBusinessCenters(BusinessCenters businessCenters);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("dayType"), DayTypeEnum.class, getDayType(), this);
			processor.processBasic(path.newSubPath("dayDistribution"), DayDistributionEnum.class, getDayDistribution(), this);
			processor.processBasic(path.newSubPath("dayOfWeek"), DayOfWeekEnum.class, getDayOfWeek(), this);
			processor.processBasic(path.newSubPath("dayFrequency"), BigDecimal.class, getDayFrequency(), this);
			processRosetta(path.newSubPath("lag"), processor, Lag.LagBuilder.class, getLag());
			processRosetta(path.newSubPath("businessCenters"), processor, BusinessCenters.BusinessCentersBuilder.class, getBusinessCenters());
		}
		

		ParametricDates.ParametricDatesBuilder prune();
	}

	/*********************** Immutable Implementation of ParametricDates  ***********************/
	class ParametricDatesImpl implements ParametricDates {
		private final DayTypeEnum dayType;
		private final DayDistributionEnum dayDistribution;
		private final List<DayOfWeekEnum> dayOfWeek;
		private final BigDecimal dayFrequency;
		private final Lag lag;
		private final BusinessCenters businessCenters;
		
		protected ParametricDatesImpl(ParametricDates.ParametricDatesBuilder builder) {
			this.dayType = builder.getDayType();
			this.dayDistribution = builder.getDayDistribution();
			this.dayOfWeek = ofNullable(builder.getDayOfWeek()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.dayFrequency = builder.getDayFrequency();
			this.lag = ofNullable(builder.getLag()).map(f->f.build()).orElse(null);
			this.businessCenters = ofNullable(builder.getBusinessCenters()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("dayType")
		public DayTypeEnum getDayType() {
			return dayType;
		}
		
		@Override
		@RosettaAttribute("dayDistribution")
		public DayDistributionEnum getDayDistribution() {
			return dayDistribution;
		}
		
		@Override
		@RosettaAttribute("dayOfWeek")
		public List<DayOfWeekEnum> getDayOfWeek() {
			return dayOfWeek;
		}
		
		@Override
		@RosettaAttribute("dayFrequency")
		public BigDecimal getDayFrequency() {
			return dayFrequency;
		}
		
		@Override
		@RosettaAttribute("lag")
		public Lag getLag() {
			return lag;
		}
		
		@Override
		@RosettaAttribute("businessCenters")
		public BusinessCenters getBusinessCenters() {
			return businessCenters;
		}
		
		@Override
		public ParametricDates build() {
			return this;
		}
		
		@Override
		public ParametricDates.ParametricDatesBuilder toBuilder() {
			ParametricDates.ParametricDatesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ParametricDates.ParametricDatesBuilder builder) {
			ofNullable(getDayType()).ifPresent(builder::setDayType);
			ofNullable(getDayDistribution()).ifPresent(builder::setDayDistribution);
			ofNullable(getDayOfWeek()).ifPresent(builder::setDayOfWeek);
			ofNullable(getDayFrequency()).ifPresent(builder::setDayFrequency);
			ofNullable(getLag()).ifPresent(builder::setLag);
			ofNullable(getBusinessCenters()).ifPresent(builder::setBusinessCenters);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ParametricDates _that = getType().cast(o);
		
			if (!Objects.equals(dayType, _that.getDayType())) return false;
			if (!Objects.equals(dayDistribution, _that.getDayDistribution())) return false;
			if (!ListEquals.listEquals(dayOfWeek, _that.getDayOfWeek())) return false;
			if (!Objects.equals(dayFrequency, _that.getDayFrequency())) return false;
			if (!Objects.equals(lag, _that.getLag())) return false;
			if (!Objects.equals(businessCenters, _that.getBusinessCenters())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (dayType != null ? dayType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (dayDistribution != null ? dayDistribution.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (dayOfWeek != null ? dayOfWeek.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (dayFrequency != null ? dayFrequency.hashCode() : 0);
			_result = 31 * _result + (lag != null ? lag.hashCode() : 0);
			_result = 31 * _result + (businessCenters != null ? businessCenters.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ParametricDates {" +
				"dayType=" + this.dayType + ", " +
				"dayDistribution=" + this.dayDistribution + ", " +
				"dayOfWeek=" + this.dayOfWeek + ", " +
				"dayFrequency=" + this.dayFrequency + ", " +
				"lag=" + this.lag + ", " +
				"businessCenters=" + this.businessCenters +
			'}';
		}
	}

	/*********************** Builder Implementation of ParametricDates  ***********************/
	class ParametricDatesBuilderImpl implements ParametricDates.ParametricDatesBuilder {
	
		protected DayTypeEnum dayType;
		protected DayDistributionEnum dayDistribution;
		protected List<DayOfWeekEnum> dayOfWeek = new ArrayList<>();
		protected BigDecimal dayFrequency;
		protected Lag.LagBuilder lag;
		protected BusinessCenters.BusinessCentersBuilder businessCenters;
	
		public ParametricDatesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("dayType")
		public DayTypeEnum getDayType() {
			return dayType;
		}
		
		@Override
		@RosettaAttribute("dayDistribution")
		public DayDistributionEnum getDayDistribution() {
			return dayDistribution;
		}
		
		@Override
		@RosettaAttribute("dayOfWeek")
		public List<DayOfWeekEnum> getDayOfWeek() {
			return dayOfWeek;
		}
		
		@Override
		@RosettaAttribute("dayFrequency")
		public BigDecimal getDayFrequency() {
			return dayFrequency;
		}
		
		@Override
		@RosettaAttribute("lag")
		public Lag.LagBuilder getLag() {
			return lag;
		}
		
		@Override
		public Lag.LagBuilder getOrCreateLag() {
			Lag.LagBuilder result;
			if (lag!=null) {
				result = lag;
			}
			else {
				result = lag = Lag.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("businessCenters")
		public BusinessCenters.BusinessCentersBuilder getBusinessCenters() {
			return businessCenters;
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder getOrCreateBusinessCenters() {
			BusinessCenters.BusinessCentersBuilder result;
			if (businessCenters!=null) {
				result = businessCenters;
			}
			else {
				result = businessCenters = BusinessCenters.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("dayType")
		public ParametricDates.ParametricDatesBuilder setDayType(DayTypeEnum dayType) {
			this.dayType = dayType==null?null:dayType;
			return this;
		}
		@Override
		@RosettaAttribute("dayDistribution")
		public ParametricDates.ParametricDatesBuilder setDayDistribution(DayDistributionEnum dayDistribution) {
			this.dayDistribution = dayDistribution==null?null:dayDistribution;
			return this;
		}
		@Override
		public ParametricDates.ParametricDatesBuilder addDayOfWeek(DayOfWeekEnum dayOfWeek) {
			if (dayOfWeek!=null) this.dayOfWeek.add(dayOfWeek);
			return this;
		}
		
		@Override
		public ParametricDates.ParametricDatesBuilder addDayOfWeek(DayOfWeekEnum dayOfWeek, int _idx) {
			getIndex(this.dayOfWeek, _idx, () -> dayOfWeek);
			return this;
		}
		@Override 
		public ParametricDates.ParametricDatesBuilder addDayOfWeek(List<? extends DayOfWeekEnum> dayOfWeeks) {
			if (dayOfWeeks != null) {
				for (DayOfWeekEnum toAdd : dayOfWeeks) {
					this.dayOfWeek.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("dayOfWeek")
		public ParametricDates.ParametricDatesBuilder setDayOfWeek(List<? extends DayOfWeekEnum> dayOfWeeks) {
			if (dayOfWeeks == null)  {
				this.dayOfWeek = new ArrayList<>();
			}
			else {
				this.dayOfWeek = dayOfWeeks.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("dayFrequency")
		public ParametricDates.ParametricDatesBuilder setDayFrequency(BigDecimal dayFrequency) {
			this.dayFrequency = dayFrequency==null?null:dayFrequency;
			return this;
		}
		@Override
		@RosettaAttribute("lag")
		public ParametricDates.ParametricDatesBuilder setLag(Lag lag) {
			this.lag = lag==null?null:lag.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("businessCenters")
		public ParametricDates.ParametricDatesBuilder setBusinessCenters(BusinessCenters businessCenters) {
			this.businessCenters = businessCenters==null?null:businessCenters.toBuilder();
			return this;
		}
		
		@Override
		public ParametricDates build() {
			return new ParametricDates.ParametricDatesImpl(this);
		}
		
		@Override
		public ParametricDates.ParametricDatesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ParametricDates.ParametricDatesBuilder prune() {
			if (lag!=null && !lag.prune().hasData()) lag = null;
			if (businessCenters!=null && !businessCenters.prune().hasData()) businessCenters = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getDayType()!=null) return true;
			if (getDayDistribution()!=null) return true;
			if (getDayOfWeek()!=null && !getDayOfWeek().isEmpty()) return true;
			if (getDayFrequency()!=null) return true;
			if (getLag()!=null && getLag().hasData()) return true;
			if (getBusinessCenters()!=null && getBusinessCenters().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ParametricDates.ParametricDatesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ParametricDates.ParametricDatesBuilder o = (ParametricDates.ParametricDatesBuilder) other;
			
			merger.mergeRosetta(getLag(), o.getLag(), this::setLag);
			merger.mergeRosetta(getBusinessCenters(), o.getBusinessCenters(), this::setBusinessCenters);
			
			merger.mergeBasic(getDayType(), o.getDayType(), this::setDayType);
			merger.mergeBasic(getDayDistribution(), o.getDayDistribution(), this::setDayDistribution);
			merger.mergeBasic(getDayOfWeek(), o.getDayOfWeek(), (Consumer<DayOfWeekEnum>) this::addDayOfWeek);
			merger.mergeBasic(getDayFrequency(), o.getDayFrequency(), this::setDayFrequency);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ParametricDates _that = getType().cast(o);
		
			if (!Objects.equals(dayType, _that.getDayType())) return false;
			if (!Objects.equals(dayDistribution, _that.getDayDistribution())) return false;
			if (!ListEquals.listEquals(dayOfWeek, _that.getDayOfWeek())) return false;
			if (!Objects.equals(dayFrequency, _that.getDayFrequency())) return false;
			if (!Objects.equals(lag, _that.getLag())) return false;
			if (!Objects.equals(businessCenters, _that.getBusinessCenters())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (dayType != null ? dayType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (dayDistribution != null ? dayDistribution.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (dayOfWeek != null ? dayOfWeek.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (dayFrequency != null ? dayFrequency.hashCode() : 0);
			_result = 31 * _result + (lag != null ? lag.hashCode() : 0);
			_result = 31 * _result + (businessCenters != null ? businessCenters.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ParametricDatesBuilder {" +
				"dayType=" + this.dayType + ", " +
				"dayDistribution=" + this.dayDistribution + ", " +
				"dayOfWeek=" + this.dayOfWeek + ", " +
				"dayFrequency=" + this.dayFrequency + ", " +
				"lag=" + this.lag + ", " +
				"businessCenters=" + this.businessCenters +
			'}';
		}
	}
}
