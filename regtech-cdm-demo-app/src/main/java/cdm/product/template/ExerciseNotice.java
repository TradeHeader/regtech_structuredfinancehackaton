package cdm.product.template;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.datetime.metafields.FieldWithMetaBusinessCenterEnum;
import cdm.base.datetime.metafields.FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder;
import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.product.template.ExerciseNotice;
import cdm.product.template.ExerciseNotice.ExerciseNoticeBuilder;
import cdm.product.template.ExerciseNotice.ExerciseNoticeBuilderImpl;
import cdm.product.template.ExerciseNotice.ExerciseNoticeImpl;
import cdm.product.template.ExerciseNoticeGiverEnum;
import cdm.product.template.meta.ExerciseNoticeMeta;
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
 * Defines to whom and where notice of execution should be given. The exerciseNoticeGiver refers to one or both of the principal parties of the trade. If present the exerciseNoticeReceiver refers to a party, other than the principal party, to whom notice should be given.
 * @version ${project.version}
 */
@RosettaDataType(value="ExerciseNotice", builder=ExerciseNotice.ExerciseNoticeBuilderImpl.class, version="${project.version}")
public interface ExerciseNotice extends RosettaModelObject {

	ExerciseNoticeMeta metaData = new ExerciseNoticeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the principal party of the trade that has the right to exercise.
	 */
	ExerciseNoticeGiverEnum getExerciseNoticeGiver();
	/**
	 * Specifies the party to which notice of exercise should be given, e.g. by the buyer of the option. Although in many cases it is the buyer of the option who sends the exercise notice to the seller of the option, this component is reused, e.g. in case of OptionEarlyTermination, either or both parties have the right to exercise.
	 */
	AncillaryRoleEnum getExerciseNoticeReceiver();
	/**
	 * Specifies the location where the exercise must be reported, e.g. where the exercise notice receiver is based.
	 */
	FieldWithMetaBusinessCenterEnum getBusinessCenter();

	/*********************** Build Methods  ***********************/
	ExerciseNotice build();
	
	ExerciseNotice.ExerciseNoticeBuilder toBuilder();
	
	static ExerciseNotice.ExerciseNoticeBuilder builder() {
		return new ExerciseNotice.ExerciseNoticeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ExerciseNotice> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ExerciseNotice> getType() {
		return ExerciseNotice.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("exerciseNoticeGiver"), ExerciseNoticeGiverEnum.class, getExerciseNoticeGiver(), this);
		processor.processBasic(path.newSubPath("exerciseNoticeReceiver"), AncillaryRoleEnum.class, getExerciseNoticeReceiver(), this);
		processRosetta(path.newSubPath("businessCenter"), processor, FieldWithMetaBusinessCenterEnum.class, getBusinessCenter());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ExerciseNoticeBuilder extends ExerciseNotice, RosettaModelObjectBuilder {
		FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder getOrCreateBusinessCenter();
		FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder getBusinessCenter();
		ExerciseNotice.ExerciseNoticeBuilder setExerciseNoticeGiver(ExerciseNoticeGiverEnum exerciseNoticeGiver);
		ExerciseNotice.ExerciseNoticeBuilder setExerciseNoticeReceiver(AncillaryRoleEnum exerciseNoticeReceiver);
		ExerciseNotice.ExerciseNoticeBuilder setBusinessCenter(FieldWithMetaBusinessCenterEnum businessCenter0);
		ExerciseNotice.ExerciseNoticeBuilder setBusinessCenterValue(BusinessCenterEnum businessCenter1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("exerciseNoticeGiver"), ExerciseNoticeGiverEnum.class, getExerciseNoticeGiver(), this);
			processor.processBasic(path.newSubPath("exerciseNoticeReceiver"), AncillaryRoleEnum.class, getExerciseNoticeReceiver(), this);
			processRosetta(path.newSubPath("businessCenter"), processor, FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder.class, getBusinessCenter());
		}
		

		ExerciseNotice.ExerciseNoticeBuilder prune();
	}

	/*********************** Immutable Implementation of ExerciseNotice  ***********************/
	class ExerciseNoticeImpl implements ExerciseNotice {
		private final ExerciseNoticeGiverEnum exerciseNoticeGiver;
		private final AncillaryRoleEnum exerciseNoticeReceiver;
		private final FieldWithMetaBusinessCenterEnum businessCenter;
		
		protected ExerciseNoticeImpl(ExerciseNotice.ExerciseNoticeBuilder builder) {
			this.exerciseNoticeGiver = builder.getExerciseNoticeGiver();
			this.exerciseNoticeReceiver = builder.getExerciseNoticeReceiver();
			this.businessCenter = ofNullable(builder.getBusinessCenter()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("exerciseNoticeGiver")
		public ExerciseNoticeGiverEnum getExerciseNoticeGiver() {
			return exerciseNoticeGiver;
		}
		
		@Override
		@RosettaAttribute("exerciseNoticeReceiver")
		public AncillaryRoleEnum getExerciseNoticeReceiver() {
			return exerciseNoticeReceiver;
		}
		
		@Override
		@RosettaAttribute("businessCenter")
		public FieldWithMetaBusinessCenterEnum getBusinessCenter() {
			return businessCenter;
		}
		
		@Override
		public ExerciseNotice build() {
			return this;
		}
		
		@Override
		public ExerciseNotice.ExerciseNoticeBuilder toBuilder() {
			ExerciseNotice.ExerciseNoticeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ExerciseNotice.ExerciseNoticeBuilder builder) {
			ofNullable(getExerciseNoticeGiver()).ifPresent(builder::setExerciseNoticeGiver);
			ofNullable(getExerciseNoticeReceiver()).ifPresent(builder::setExerciseNoticeReceiver);
			ofNullable(getBusinessCenter()).ifPresent(builder::setBusinessCenter);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ExerciseNotice _that = getType().cast(o);
		
			if (!Objects.equals(exerciseNoticeGiver, _that.getExerciseNoticeGiver())) return false;
			if (!Objects.equals(exerciseNoticeReceiver, _that.getExerciseNoticeReceiver())) return false;
			if (!Objects.equals(businessCenter, _that.getBusinessCenter())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (exerciseNoticeGiver != null ? exerciseNoticeGiver.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (exerciseNoticeReceiver != null ? exerciseNoticeReceiver.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (businessCenter != null ? businessCenter.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExerciseNotice {" +
				"exerciseNoticeGiver=" + this.exerciseNoticeGiver + ", " +
				"exerciseNoticeReceiver=" + this.exerciseNoticeReceiver + ", " +
				"businessCenter=" + this.businessCenter +
			'}';
		}
	}

	/*********************** Builder Implementation of ExerciseNotice  ***********************/
	class ExerciseNoticeBuilderImpl implements ExerciseNotice.ExerciseNoticeBuilder {
	
		protected ExerciseNoticeGiverEnum exerciseNoticeGiver;
		protected AncillaryRoleEnum exerciseNoticeReceiver;
		protected FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder businessCenter;
	
		public ExerciseNoticeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("exerciseNoticeGiver")
		public ExerciseNoticeGiverEnum getExerciseNoticeGiver() {
			return exerciseNoticeGiver;
		}
		
		@Override
		@RosettaAttribute("exerciseNoticeReceiver")
		public AncillaryRoleEnum getExerciseNoticeReceiver() {
			return exerciseNoticeReceiver;
		}
		
		@Override
		@RosettaAttribute("businessCenter")
		public FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder getBusinessCenter() {
			return businessCenter;
		}
		
		@Override
		public FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder getOrCreateBusinessCenter() {
			FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder result;
			if (businessCenter!=null) {
				result = businessCenter;
			}
			else {
				result = businessCenter = FieldWithMetaBusinessCenterEnum.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("exerciseNoticeGiver")
		public ExerciseNotice.ExerciseNoticeBuilder setExerciseNoticeGiver(ExerciseNoticeGiverEnum exerciseNoticeGiver) {
			this.exerciseNoticeGiver = exerciseNoticeGiver==null?null:exerciseNoticeGiver;
			return this;
		}
		@Override
		@RosettaAttribute("exerciseNoticeReceiver")
		public ExerciseNotice.ExerciseNoticeBuilder setExerciseNoticeReceiver(AncillaryRoleEnum exerciseNoticeReceiver) {
			this.exerciseNoticeReceiver = exerciseNoticeReceiver==null?null:exerciseNoticeReceiver;
			return this;
		}
		@Override
		@RosettaAttribute("businessCenter")
		public ExerciseNotice.ExerciseNoticeBuilder setBusinessCenter(FieldWithMetaBusinessCenterEnum businessCenter) {
			this.businessCenter = businessCenter==null?null:businessCenter.toBuilder();
			return this;
		}
		@Override
		public ExerciseNotice.ExerciseNoticeBuilder setBusinessCenterValue(BusinessCenterEnum businessCenter) {
			this.getOrCreateBusinessCenter().setValue(businessCenter);
			return this;
		}
		
		@Override
		public ExerciseNotice build() {
			return new ExerciseNotice.ExerciseNoticeImpl(this);
		}
		
		@Override
		public ExerciseNotice.ExerciseNoticeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExerciseNotice.ExerciseNoticeBuilder prune() {
			if (businessCenter!=null && !businessCenter.prune().hasData()) businessCenter = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getExerciseNoticeGiver()!=null) return true;
			if (getExerciseNoticeReceiver()!=null) return true;
			if (getBusinessCenter()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExerciseNotice.ExerciseNoticeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ExerciseNotice.ExerciseNoticeBuilder o = (ExerciseNotice.ExerciseNoticeBuilder) other;
			
			merger.mergeRosetta(getBusinessCenter(), o.getBusinessCenter(), this::setBusinessCenter);
			
			merger.mergeBasic(getExerciseNoticeGiver(), o.getExerciseNoticeGiver(), this::setExerciseNoticeGiver);
			merger.mergeBasic(getExerciseNoticeReceiver(), o.getExerciseNoticeReceiver(), this::setExerciseNoticeReceiver);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ExerciseNotice _that = getType().cast(o);
		
			if (!Objects.equals(exerciseNoticeGiver, _that.getExerciseNoticeGiver())) return false;
			if (!Objects.equals(exerciseNoticeReceiver, _that.getExerciseNoticeReceiver())) return false;
			if (!Objects.equals(businessCenter, _that.getBusinessCenter())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (exerciseNoticeGiver != null ? exerciseNoticeGiver.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (exerciseNoticeReceiver != null ? exerciseNoticeReceiver.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (businessCenter != null ? businessCenter.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExerciseNoticeBuilder {" +
				"exerciseNoticeGiver=" + this.exerciseNoticeGiver + ", " +
				"exerciseNoticeReceiver=" + this.exerciseNoticeReceiver + ", " +
				"businessCenter=" + this.businessCenter +
			'}';
		}
	}
}
