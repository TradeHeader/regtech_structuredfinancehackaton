package cdm.product.template;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.AdjustableOrRelativeDates;
import cdm.base.datetime.BusinessCenterTime;
import cdm.product.template.EuropeanExercise;
import cdm.product.template.EuropeanExercise.EuropeanExerciseBuilder;
import cdm.product.template.EuropeanExercise.EuropeanExerciseBuilderImpl;
import cdm.product.template.EuropeanExercise.EuropeanExerciseImpl;
import cdm.product.template.ExerciseFee;
import cdm.product.template.ExpirationTimeTypeEnum;
import cdm.product.template.PartialExercise;
import cdm.product.template.meta.EuropeanExerciseMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A class defining the exercise period for a European style option together with any rules governing the notional amount of the underlying which can be exercised on any given exercise date and any associated exercise fees.
 * @version ${project.version}
 */
@RosettaDataType(value="EuropeanExercise", builder=EuropeanExercise.EuropeanExerciseBuilderImpl.class, version="${project.version}")
public interface EuropeanExercise extends RosettaModelObject, GlobalKey {

	EuropeanExerciseMeta metaData = new EuropeanExerciseMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The last day within an exercise period for an American style option. For a European style option it is the only day within the exercise period.
	 */
	List<? extends AdjustableOrRelativeDate> getExpirationDate();
	/**
	 * The effective date on the underlying product if the option is exercised.  For example, for a swaption it is the swap effective date, for an option on an FX spot or forward it is the value date for settlement, and in an extendible/cancelable provision it is the swap termination date, which is the date on which the termination is effective.
	 */
	AdjustableOrRelativeDates getRelevantUnderlyingDate();
	/**
	 * The earliest time at which notice of exercise can be given by the buyer to the seller (or seller&#39;s agent) on the expiration date.
	 */
	BusinessCenterTime getEarliestExerciseTime();
	/**
	 * The latest time for exercise on expirationDate.
	 */
	BusinessCenterTime getExpirationTime();
	/**
	 * The time of day at which the equity option expires, for example the official closing time of the exchange.
	 */
	ExpirationTimeTypeEnum getExpirationTimeType();
	/**
	 * As defined in the 2000 ISDA Definitions, Section 12.3. Partial Exercise, the buyer of the option has the right to exercise all or less than all the notional amount of the underlying swap on the expiration date, but may not exercise less than the minimum notional amount, and if an integral multiple amount is specified, the notional amount exercised must be equal to, or be an integral multiple of, the integral multiple amount.
	 */
	PartialExercise getPartialExercise();
	/**
	 * A fee to be paid on exercise. This could be represented as an amount or a rate and notional reference on which to apply the rate.
	 */
	ExerciseFee getExerciseFee();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	EuropeanExercise build();
	
	EuropeanExercise.EuropeanExerciseBuilder toBuilder();
	
	static EuropeanExercise.EuropeanExerciseBuilder builder() {
		return new EuropeanExercise.EuropeanExerciseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends EuropeanExercise> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends EuropeanExercise> getType() {
		return EuropeanExercise.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("expirationDate"), processor, AdjustableOrRelativeDate.class, getExpirationDate());
		processRosetta(path.newSubPath("relevantUnderlyingDate"), processor, AdjustableOrRelativeDates.class, getRelevantUnderlyingDate());
		processRosetta(path.newSubPath("earliestExerciseTime"), processor, BusinessCenterTime.class, getEarliestExerciseTime());
		processRosetta(path.newSubPath("expirationTime"), processor, BusinessCenterTime.class, getExpirationTime());
		processor.processBasic(path.newSubPath("expirationTimeType"), ExpirationTimeTypeEnum.class, getExpirationTimeType(), this);
		processRosetta(path.newSubPath("partialExercise"), processor, PartialExercise.class, getPartialExercise());
		processRosetta(path.newSubPath("exerciseFee"), processor, ExerciseFee.class, getExerciseFee());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface EuropeanExerciseBuilder extends EuropeanExercise, RosettaModelObjectBuilder {
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateExpirationDate(int _index);
		List<? extends AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder> getExpirationDate();
		AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder getOrCreateRelevantUnderlyingDate();
		AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder getRelevantUnderlyingDate();
		BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateEarliestExerciseTime();
		BusinessCenterTime.BusinessCenterTimeBuilder getEarliestExerciseTime();
		BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateExpirationTime();
		BusinessCenterTime.BusinessCenterTimeBuilder getExpirationTime();
		PartialExercise.PartialExerciseBuilder getOrCreatePartialExercise();
		PartialExercise.PartialExerciseBuilder getPartialExercise();
		ExerciseFee.ExerciseFeeBuilder getOrCreateExerciseFee();
		ExerciseFee.ExerciseFeeBuilder getExerciseFee();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		EuropeanExercise.EuropeanExerciseBuilder addExpirationDate(AdjustableOrRelativeDate expirationDate0);
		EuropeanExercise.EuropeanExerciseBuilder addExpirationDate(AdjustableOrRelativeDate expirationDate1, int _idx);
		EuropeanExercise.EuropeanExerciseBuilder addExpirationDate(List<? extends AdjustableOrRelativeDate> expirationDate2);
		EuropeanExercise.EuropeanExerciseBuilder setExpirationDate(List<? extends AdjustableOrRelativeDate> expirationDate3);
		EuropeanExercise.EuropeanExerciseBuilder setRelevantUnderlyingDate(AdjustableOrRelativeDates relevantUnderlyingDate);
		EuropeanExercise.EuropeanExerciseBuilder setEarliestExerciseTime(BusinessCenterTime earliestExerciseTime);
		EuropeanExercise.EuropeanExerciseBuilder setExpirationTime(BusinessCenterTime expirationTime);
		EuropeanExercise.EuropeanExerciseBuilder setExpirationTimeType(ExpirationTimeTypeEnum expirationTimeType);
		EuropeanExercise.EuropeanExerciseBuilder setPartialExercise(PartialExercise partialExercise);
		EuropeanExercise.EuropeanExerciseBuilder setExerciseFee(ExerciseFee exerciseFee);
		EuropeanExercise.EuropeanExerciseBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("expirationDate"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getExpirationDate());
			processRosetta(path.newSubPath("relevantUnderlyingDate"), processor, AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder.class, getRelevantUnderlyingDate());
			processRosetta(path.newSubPath("earliestExerciseTime"), processor, BusinessCenterTime.BusinessCenterTimeBuilder.class, getEarliestExerciseTime());
			processRosetta(path.newSubPath("expirationTime"), processor, BusinessCenterTime.BusinessCenterTimeBuilder.class, getExpirationTime());
			processor.processBasic(path.newSubPath("expirationTimeType"), ExpirationTimeTypeEnum.class, getExpirationTimeType(), this);
			processRosetta(path.newSubPath("partialExercise"), processor, PartialExercise.PartialExerciseBuilder.class, getPartialExercise());
			processRosetta(path.newSubPath("exerciseFee"), processor, ExerciseFee.ExerciseFeeBuilder.class, getExerciseFee());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		EuropeanExercise.EuropeanExerciseBuilder prune();
	}

	/*********************** Immutable Implementation of EuropeanExercise  ***********************/
	class EuropeanExerciseImpl implements EuropeanExercise {
		private final List<? extends AdjustableOrRelativeDate> expirationDate;
		private final AdjustableOrRelativeDates relevantUnderlyingDate;
		private final BusinessCenterTime earliestExerciseTime;
		private final BusinessCenterTime expirationTime;
		private final ExpirationTimeTypeEnum expirationTimeType;
		private final PartialExercise partialExercise;
		private final ExerciseFee exerciseFee;
		private final MetaFields meta;
		
		protected EuropeanExerciseImpl(EuropeanExercise.EuropeanExerciseBuilder builder) {
			this.expirationDate = ofNullable(builder.getExpirationDate()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.relevantUnderlyingDate = ofNullable(builder.getRelevantUnderlyingDate()).map(f->f.build()).orElse(null);
			this.earliestExerciseTime = ofNullable(builder.getEarliestExerciseTime()).map(f->f.build()).orElse(null);
			this.expirationTime = ofNullable(builder.getExpirationTime()).map(f->f.build()).orElse(null);
			this.expirationTimeType = builder.getExpirationTimeType();
			this.partialExercise = ofNullable(builder.getPartialExercise()).map(f->f.build()).orElse(null);
			this.exerciseFee = ofNullable(builder.getExerciseFee()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("expirationDate")
		public List<? extends AdjustableOrRelativeDate> getExpirationDate() {
			return expirationDate;
		}
		
		@Override
		@RosettaAttribute("relevantUnderlyingDate")
		public AdjustableOrRelativeDates getRelevantUnderlyingDate() {
			return relevantUnderlyingDate;
		}
		
		@Override
		@RosettaAttribute("earliestExerciseTime")
		public BusinessCenterTime getEarliestExerciseTime() {
			return earliestExerciseTime;
		}
		
		@Override
		@RosettaAttribute("expirationTime")
		public BusinessCenterTime getExpirationTime() {
			return expirationTime;
		}
		
		@Override
		@RosettaAttribute("expirationTimeType")
		public ExpirationTimeTypeEnum getExpirationTimeType() {
			return expirationTimeType;
		}
		
		@Override
		@RosettaAttribute("partialExercise")
		public PartialExercise getPartialExercise() {
			return partialExercise;
		}
		
		@Override
		@RosettaAttribute("exerciseFee")
		public ExerciseFee getExerciseFee() {
			return exerciseFee;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public EuropeanExercise build() {
			return this;
		}
		
		@Override
		public EuropeanExercise.EuropeanExerciseBuilder toBuilder() {
			EuropeanExercise.EuropeanExerciseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(EuropeanExercise.EuropeanExerciseBuilder builder) {
			ofNullable(getExpirationDate()).ifPresent(builder::setExpirationDate);
			ofNullable(getRelevantUnderlyingDate()).ifPresent(builder::setRelevantUnderlyingDate);
			ofNullable(getEarliestExerciseTime()).ifPresent(builder::setEarliestExerciseTime);
			ofNullable(getExpirationTime()).ifPresent(builder::setExpirationTime);
			ofNullable(getExpirationTimeType()).ifPresent(builder::setExpirationTimeType);
			ofNullable(getPartialExercise()).ifPresent(builder::setPartialExercise);
			ofNullable(getExerciseFee()).ifPresent(builder::setExerciseFee);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			EuropeanExercise _that = getType().cast(o);
		
			if (!ListEquals.listEquals(expirationDate, _that.getExpirationDate())) return false;
			if (!Objects.equals(relevantUnderlyingDate, _that.getRelevantUnderlyingDate())) return false;
			if (!Objects.equals(earliestExerciseTime, _that.getEarliestExerciseTime())) return false;
			if (!Objects.equals(expirationTime, _that.getExpirationTime())) return false;
			if (!Objects.equals(expirationTimeType, _that.getExpirationTimeType())) return false;
			if (!Objects.equals(partialExercise, _that.getPartialExercise())) return false;
			if (!Objects.equals(exerciseFee, _that.getExerciseFee())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (expirationDate != null ? expirationDate.hashCode() : 0);
			_result = 31 * _result + (relevantUnderlyingDate != null ? relevantUnderlyingDate.hashCode() : 0);
			_result = 31 * _result + (earliestExerciseTime != null ? earliestExerciseTime.hashCode() : 0);
			_result = 31 * _result + (expirationTime != null ? expirationTime.hashCode() : 0);
			_result = 31 * _result + (expirationTimeType != null ? expirationTimeType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (partialExercise != null ? partialExercise.hashCode() : 0);
			_result = 31 * _result + (exerciseFee != null ? exerciseFee.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EuropeanExercise {" +
				"expirationDate=" + this.expirationDate + ", " +
				"relevantUnderlyingDate=" + this.relevantUnderlyingDate + ", " +
				"earliestExerciseTime=" + this.earliestExerciseTime + ", " +
				"expirationTime=" + this.expirationTime + ", " +
				"expirationTimeType=" + this.expirationTimeType + ", " +
				"partialExercise=" + this.partialExercise + ", " +
				"exerciseFee=" + this.exerciseFee + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of EuropeanExercise  ***********************/
	class EuropeanExerciseBuilderImpl implements EuropeanExercise.EuropeanExerciseBuilder, GlobalKeyBuilder {
	
		protected List<AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder> expirationDate = new ArrayList<>();
		protected AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder relevantUnderlyingDate;
		protected BusinessCenterTime.BusinessCenterTimeBuilder earliestExerciseTime;
		protected BusinessCenterTime.BusinessCenterTimeBuilder expirationTime;
		protected ExpirationTimeTypeEnum expirationTimeType;
		protected PartialExercise.PartialExerciseBuilder partialExercise;
		protected ExerciseFee.ExerciseFeeBuilder exerciseFee;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public EuropeanExerciseBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("expirationDate")
		public List<? extends AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder> getExpirationDate() {
			return expirationDate;
		}
		
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateExpirationDate(int _index) {
		
			if (expirationDate==null) {
				this.expirationDate = new ArrayList<>();
			}
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			return getIndex(expirationDate, _index, () -> {
						AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder newExpirationDate = AdjustableOrRelativeDate.builder();
						return newExpirationDate;
					});
		}
		
		@Override
		@RosettaAttribute("relevantUnderlyingDate")
		public AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder getRelevantUnderlyingDate() {
			return relevantUnderlyingDate;
		}
		
		@Override
		public AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder getOrCreateRelevantUnderlyingDate() {
			AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder result;
			if (relevantUnderlyingDate!=null) {
				result = relevantUnderlyingDate;
			}
			else {
				result = relevantUnderlyingDate = AdjustableOrRelativeDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("earliestExerciseTime")
		public BusinessCenterTime.BusinessCenterTimeBuilder getEarliestExerciseTime() {
			return earliestExerciseTime;
		}
		
		@Override
		public BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateEarliestExerciseTime() {
			BusinessCenterTime.BusinessCenterTimeBuilder result;
			if (earliestExerciseTime!=null) {
				result = earliestExerciseTime;
			}
			else {
				result = earliestExerciseTime = BusinessCenterTime.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("expirationTime")
		public BusinessCenterTime.BusinessCenterTimeBuilder getExpirationTime() {
			return expirationTime;
		}
		
		@Override
		public BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateExpirationTime() {
			BusinessCenterTime.BusinessCenterTimeBuilder result;
			if (expirationTime!=null) {
				result = expirationTime;
			}
			else {
				result = expirationTime = BusinessCenterTime.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("expirationTimeType")
		public ExpirationTimeTypeEnum getExpirationTimeType() {
			return expirationTimeType;
		}
		
		@Override
		@RosettaAttribute("partialExercise")
		public PartialExercise.PartialExerciseBuilder getPartialExercise() {
			return partialExercise;
		}
		
		@Override
		public PartialExercise.PartialExerciseBuilder getOrCreatePartialExercise() {
			PartialExercise.PartialExerciseBuilder result;
			if (partialExercise!=null) {
				result = partialExercise;
			}
			else {
				result = partialExercise = PartialExercise.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("exerciseFee")
		public ExerciseFee.ExerciseFeeBuilder getExerciseFee() {
			return exerciseFee;
		}
		
		@Override
		public ExerciseFee.ExerciseFeeBuilder getOrCreateExerciseFee() {
			ExerciseFee.ExerciseFeeBuilder result;
			if (exerciseFee!=null) {
				result = exerciseFee;
			}
			else {
				result = exerciseFee = ExerciseFee.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		public EuropeanExercise.EuropeanExerciseBuilder addExpirationDate(AdjustableOrRelativeDate expirationDate) {
			if (expirationDate!=null) this.expirationDate.add(expirationDate.toBuilder());
			return this;
		}
		
		@Override
		public EuropeanExercise.EuropeanExerciseBuilder addExpirationDate(AdjustableOrRelativeDate expirationDate, int _idx) {
			getIndex(this.expirationDate, _idx, () -> expirationDate.toBuilder());
			return this;
		}
		@Override 
		public EuropeanExercise.EuropeanExerciseBuilder addExpirationDate(List<? extends AdjustableOrRelativeDate> expirationDates) {
			if (expirationDates != null) {
				for (AdjustableOrRelativeDate toAdd : expirationDates) {
					this.expirationDate.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("expirationDate")
		public EuropeanExercise.EuropeanExerciseBuilder setExpirationDate(List<? extends AdjustableOrRelativeDate> expirationDates) {
			if (expirationDates == null)  {
				this.expirationDate = new ArrayList<>();
			}
			else {
				this.expirationDate = expirationDates.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("relevantUnderlyingDate")
		public EuropeanExercise.EuropeanExerciseBuilder setRelevantUnderlyingDate(AdjustableOrRelativeDates relevantUnderlyingDate) {
			this.relevantUnderlyingDate = relevantUnderlyingDate==null?null:relevantUnderlyingDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("earliestExerciseTime")
		public EuropeanExercise.EuropeanExerciseBuilder setEarliestExerciseTime(BusinessCenterTime earliestExerciseTime) {
			this.earliestExerciseTime = earliestExerciseTime==null?null:earliestExerciseTime.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("expirationTime")
		public EuropeanExercise.EuropeanExerciseBuilder setExpirationTime(BusinessCenterTime expirationTime) {
			this.expirationTime = expirationTime==null?null:expirationTime.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("expirationTimeType")
		public EuropeanExercise.EuropeanExerciseBuilder setExpirationTimeType(ExpirationTimeTypeEnum expirationTimeType) {
			this.expirationTimeType = expirationTimeType==null?null:expirationTimeType;
			return this;
		}
		@Override
		@RosettaAttribute("partialExercise")
		public EuropeanExercise.EuropeanExerciseBuilder setPartialExercise(PartialExercise partialExercise) {
			this.partialExercise = partialExercise==null?null:partialExercise.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("exerciseFee")
		public EuropeanExercise.EuropeanExerciseBuilder setExerciseFee(ExerciseFee exerciseFee) {
			this.exerciseFee = exerciseFee==null?null:exerciseFee.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public EuropeanExercise.EuropeanExerciseBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public EuropeanExercise build() {
			return new EuropeanExercise.EuropeanExerciseImpl(this);
		}
		
		@Override
		public EuropeanExercise.EuropeanExerciseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EuropeanExercise.EuropeanExerciseBuilder prune() {
			expirationDate = expirationDate.stream().filter(b->b!=null).<AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (relevantUnderlyingDate!=null && !relevantUnderlyingDate.prune().hasData()) relevantUnderlyingDate = null;
			if (earliestExerciseTime!=null && !earliestExerciseTime.prune().hasData()) earliestExerciseTime = null;
			if (expirationTime!=null && !expirationTime.prune().hasData()) expirationTime = null;
			if (partialExercise!=null && !partialExercise.prune().hasData()) partialExercise = null;
			if (exerciseFee!=null && !exerciseFee.prune().hasData()) exerciseFee = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getExpirationDate()!=null && getExpirationDate().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getRelevantUnderlyingDate()!=null && getRelevantUnderlyingDate().hasData()) return true;
			if (getEarliestExerciseTime()!=null && getEarliestExerciseTime().hasData()) return true;
			if (getExpirationTime()!=null && getExpirationTime().hasData()) return true;
			if (getExpirationTimeType()!=null) return true;
			if (getPartialExercise()!=null && getPartialExercise().hasData()) return true;
			if (getExerciseFee()!=null && getExerciseFee().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EuropeanExercise.EuropeanExerciseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			EuropeanExercise.EuropeanExerciseBuilder o = (EuropeanExercise.EuropeanExerciseBuilder) other;
			
			merger.mergeRosetta(getExpirationDate(), o.getExpirationDate(), this::getOrCreateExpirationDate);
			merger.mergeRosetta(getRelevantUnderlyingDate(), o.getRelevantUnderlyingDate(), this::setRelevantUnderlyingDate);
			merger.mergeRosetta(getEarliestExerciseTime(), o.getEarliestExerciseTime(), this::setEarliestExerciseTime);
			merger.mergeRosetta(getExpirationTime(), o.getExpirationTime(), this::setExpirationTime);
			merger.mergeRosetta(getPartialExercise(), o.getPartialExercise(), this::setPartialExercise);
			merger.mergeRosetta(getExerciseFee(), o.getExerciseFee(), this::setExerciseFee);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getExpirationTimeType(), o.getExpirationTimeType(), this::setExpirationTimeType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			EuropeanExercise _that = getType().cast(o);
		
			if (!ListEquals.listEquals(expirationDate, _that.getExpirationDate())) return false;
			if (!Objects.equals(relevantUnderlyingDate, _that.getRelevantUnderlyingDate())) return false;
			if (!Objects.equals(earliestExerciseTime, _that.getEarliestExerciseTime())) return false;
			if (!Objects.equals(expirationTime, _that.getExpirationTime())) return false;
			if (!Objects.equals(expirationTimeType, _that.getExpirationTimeType())) return false;
			if (!Objects.equals(partialExercise, _that.getPartialExercise())) return false;
			if (!Objects.equals(exerciseFee, _that.getExerciseFee())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (expirationDate != null ? expirationDate.hashCode() : 0);
			_result = 31 * _result + (relevantUnderlyingDate != null ? relevantUnderlyingDate.hashCode() : 0);
			_result = 31 * _result + (earliestExerciseTime != null ? earliestExerciseTime.hashCode() : 0);
			_result = 31 * _result + (expirationTime != null ? expirationTime.hashCode() : 0);
			_result = 31 * _result + (expirationTimeType != null ? expirationTimeType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (partialExercise != null ? partialExercise.hashCode() : 0);
			_result = 31 * _result + (exerciseFee != null ? exerciseFee.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EuropeanExerciseBuilder {" +
				"expirationDate=" + this.expirationDate + ", " +
				"relevantUnderlyingDate=" + this.relevantUnderlyingDate + ", " +
				"earliestExerciseTime=" + this.earliestExerciseTime + ", " +
				"expirationTime=" + this.expirationTime + ", " +
				"expirationTimeType=" + this.expirationTimeType + ", " +
				"partialExercise=" + this.partialExercise + ", " +
				"exerciseFee=" + this.exerciseFee + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
