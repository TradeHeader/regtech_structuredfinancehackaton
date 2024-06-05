package cdm.product.common.schedule;

import cdm.product.common.schedule.AveragingObservationList;
import cdm.product.common.schedule.AveragingObservationList.AveragingObservationListBuilder;
import cdm.product.common.schedule.AveragingObservationList.AveragingObservationListBuilderImpl;
import cdm.product.common.schedule.AveragingObservationList.AveragingObservationListImpl;
import cdm.product.common.schedule.WeightedAveragingObservation;
import cdm.product.common.schedule.meta.AveragingObservationListMeta;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * An unordered list of weighted averaging observations.
 * @version ${project.version}
 */
@RosettaDataType(value="AveragingObservationList", builder=AveragingObservationList.AveragingObservationListBuilderImpl.class, version="${project.version}")
public interface AveragingObservationList extends RosettaModelObject {

	AveragingObservationListMeta metaData = new AveragingObservationListMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A single weighted averaging observation.
	 */
	List<? extends WeightedAveragingObservation> getAveragingObservation();

	/*********************** Build Methods  ***********************/
	AveragingObservationList build();
	
	AveragingObservationList.AveragingObservationListBuilder toBuilder();
	
	static AveragingObservationList.AveragingObservationListBuilder builder() {
		return new AveragingObservationList.AveragingObservationListBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AveragingObservationList> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AveragingObservationList> getType() {
		return AveragingObservationList.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("averagingObservation"), processor, WeightedAveragingObservation.class, getAveragingObservation());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AveragingObservationListBuilder extends AveragingObservationList, RosettaModelObjectBuilder {
		WeightedAveragingObservation.WeightedAveragingObservationBuilder getOrCreateAveragingObservation(int _index);
		List<? extends WeightedAveragingObservation.WeightedAveragingObservationBuilder> getAveragingObservation();
		AveragingObservationList.AveragingObservationListBuilder addAveragingObservation(WeightedAveragingObservation averagingObservation0);
		AveragingObservationList.AveragingObservationListBuilder addAveragingObservation(WeightedAveragingObservation averagingObservation1, int _idx);
		AveragingObservationList.AveragingObservationListBuilder addAveragingObservation(List<? extends WeightedAveragingObservation> averagingObservation2);
		AveragingObservationList.AveragingObservationListBuilder setAveragingObservation(List<? extends WeightedAveragingObservation> averagingObservation3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("averagingObservation"), processor, WeightedAveragingObservation.WeightedAveragingObservationBuilder.class, getAveragingObservation());
		}
		

		AveragingObservationList.AveragingObservationListBuilder prune();
	}

	/*********************** Immutable Implementation of AveragingObservationList  ***********************/
	class AveragingObservationListImpl implements AveragingObservationList {
		private final List<? extends WeightedAveragingObservation> averagingObservation;
		
		protected AveragingObservationListImpl(AveragingObservationList.AveragingObservationListBuilder builder) {
			this.averagingObservation = ofNullable(builder.getAveragingObservation()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("averagingObservation")
		public List<? extends WeightedAveragingObservation> getAveragingObservation() {
			return averagingObservation;
		}
		
		@Override
		public AveragingObservationList build() {
			return this;
		}
		
		@Override
		public AveragingObservationList.AveragingObservationListBuilder toBuilder() {
			AveragingObservationList.AveragingObservationListBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AveragingObservationList.AveragingObservationListBuilder builder) {
			ofNullable(getAveragingObservation()).ifPresent(builder::setAveragingObservation);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AveragingObservationList _that = getType().cast(o);
		
			if (!ListEquals.listEquals(averagingObservation, _that.getAveragingObservation())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (averagingObservation != null ? averagingObservation.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AveragingObservationList {" +
				"averagingObservation=" + this.averagingObservation +
			'}';
		}
	}

	/*********************** Builder Implementation of AveragingObservationList  ***********************/
	class AveragingObservationListBuilderImpl implements AveragingObservationList.AveragingObservationListBuilder {
	
		protected List<WeightedAveragingObservation.WeightedAveragingObservationBuilder> averagingObservation = new ArrayList<>();
	
		public AveragingObservationListBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("averagingObservation")
		public List<? extends WeightedAveragingObservation.WeightedAveragingObservationBuilder> getAveragingObservation() {
			return averagingObservation;
		}
		
		public WeightedAveragingObservation.WeightedAveragingObservationBuilder getOrCreateAveragingObservation(int _index) {
		
			if (averagingObservation==null) {
				this.averagingObservation = new ArrayList<>();
			}
			WeightedAveragingObservation.WeightedAveragingObservationBuilder result;
			return getIndex(averagingObservation, _index, () -> {
						WeightedAveragingObservation.WeightedAveragingObservationBuilder newAveragingObservation = WeightedAveragingObservation.builder();
						return newAveragingObservation;
					});
		}
		
	
		@Override
		public AveragingObservationList.AveragingObservationListBuilder addAveragingObservation(WeightedAveragingObservation averagingObservation) {
			if (averagingObservation!=null) this.averagingObservation.add(averagingObservation.toBuilder());
			return this;
		}
		
		@Override
		public AveragingObservationList.AveragingObservationListBuilder addAveragingObservation(WeightedAveragingObservation averagingObservation, int _idx) {
			getIndex(this.averagingObservation, _idx, () -> averagingObservation.toBuilder());
			return this;
		}
		@Override 
		public AveragingObservationList.AveragingObservationListBuilder addAveragingObservation(List<? extends WeightedAveragingObservation> averagingObservations) {
			if (averagingObservations != null) {
				for (WeightedAveragingObservation toAdd : averagingObservations) {
					this.averagingObservation.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("averagingObservation")
		public AveragingObservationList.AveragingObservationListBuilder setAveragingObservation(List<? extends WeightedAveragingObservation> averagingObservations) {
			if (averagingObservations == null)  {
				this.averagingObservation = new ArrayList<>();
			}
			else {
				this.averagingObservation = averagingObservations.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public AveragingObservationList build() {
			return new AveragingObservationList.AveragingObservationListImpl(this);
		}
		
		@Override
		public AveragingObservationList.AveragingObservationListBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AveragingObservationList.AveragingObservationListBuilder prune() {
			averagingObservation = averagingObservation.stream().filter(b->b!=null).<WeightedAveragingObservation.WeightedAveragingObservationBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getAveragingObservation()!=null && getAveragingObservation().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AveragingObservationList.AveragingObservationListBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AveragingObservationList.AveragingObservationListBuilder o = (AveragingObservationList.AveragingObservationListBuilder) other;
			
			merger.mergeRosetta(getAveragingObservation(), o.getAveragingObservation(), this::getOrCreateAveragingObservation);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AveragingObservationList _that = getType().cast(o);
		
			if (!ListEquals.listEquals(averagingObservation, _that.getAveragingObservation())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (averagingObservation != null ? averagingObservation.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AveragingObservationListBuilder {" +
				"averagingObservation=" + this.averagingObservation +
			'}';
		}
	}
}
