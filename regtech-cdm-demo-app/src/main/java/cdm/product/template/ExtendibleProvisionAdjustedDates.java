package cdm.product.template;

import cdm.product.template.ExtendibleProvisionAdjustedDates;
import cdm.product.template.ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder;
import cdm.product.template.ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilderImpl;
import cdm.product.template.ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesImpl;
import cdm.product.template.ExtensionEvent;
import cdm.product.template.meta.ExtendibleProvisionAdjustedDatesMeta;
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
 * A data defining:  the adjusted dates associated with a provision to extend a swap.
 * @version ${project.version}
 */
@RosettaDataType(value="ExtendibleProvisionAdjustedDates", builder=ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilderImpl.class, version="${project.version}")
public interface ExtendibleProvisionAdjustedDates extends RosettaModelObject {

	ExtendibleProvisionAdjustedDatesMeta metaData = new ExtendibleProvisionAdjustedDatesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The adjusted dates associated with a single extendible exercise date.
	 */
	List<? extends ExtensionEvent> getExtensionEvent();

	/*********************** Build Methods  ***********************/
	ExtendibleProvisionAdjustedDates build();
	
	ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder toBuilder();
	
	static ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder builder() {
		return new ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ExtendibleProvisionAdjustedDates> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ExtendibleProvisionAdjustedDates> getType() {
		return ExtendibleProvisionAdjustedDates.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("extensionEvent"), processor, ExtensionEvent.class, getExtensionEvent());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ExtendibleProvisionAdjustedDatesBuilder extends ExtendibleProvisionAdjustedDates, RosettaModelObjectBuilder {
		ExtensionEvent.ExtensionEventBuilder getOrCreateExtensionEvent(int _index);
		List<? extends ExtensionEvent.ExtensionEventBuilder> getExtensionEvent();
		ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder addExtensionEvent(ExtensionEvent extensionEvent0);
		ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder addExtensionEvent(ExtensionEvent extensionEvent1, int _idx);
		ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder addExtensionEvent(List<? extends ExtensionEvent> extensionEvent2);
		ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder setExtensionEvent(List<? extends ExtensionEvent> extensionEvent3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("extensionEvent"), processor, ExtensionEvent.ExtensionEventBuilder.class, getExtensionEvent());
		}
		

		ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder prune();
	}

	/*********************** Immutable Implementation of ExtendibleProvisionAdjustedDates  ***********************/
	class ExtendibleProvisionAdjustedDatesImpl implements ExtendibleProvisionAdjustedDates {
		private final List<? extends ExtensionEvent> extensionEvent;
		
		protected ExtendibleProvisionAdjustedDatesImpl(ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder builder) {
			this.extensionEvent = ofNullable(builder.getExtensionEvent()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("extensionEvent")
		public List<? extends ExtensionEvent> getExtensionEvent() {
			return extensionEvent;
		}
		
		@Override
		public ExtendibleProvisionAdjustedDates build() {
			return this;
		}
		
		@Override
		public ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder toBuilder() {
			ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder builder) {
			ofNullable(getExtensionEvent()).ifPresent(builder::setExtensionEvent);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ExtendibleProvisionAdjustedDates _that = getType().cast(o);
		
			if (!ListEquals.listEquals(extensionEvent, _that.getExtensionEvent())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (extensionEvent != null ? extensionEvent.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExtendibleProvisionAdjustedDates {" +
				"extensionEvent=" + this.extensionEvent +
			'}';
		}
	}

	/*********************** Builder Implementation of ExtendibleProvisionAdjustedDates  ***********************/
	class ExtendibleProvisionAdjustedDatesBuilderImpl implements ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder {
	
		protected List<ExtensionEvent.ExtensionEventBuilder> extensionEvent = new ArrayList<>();
	
		public ExtendibleProvisionAdjustedDatesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("extensionEvent")
		public List<? extends ExtensionEvent.ExtensionEventBuilder> getExtensionEvent() {
			return extensionEvent;
		}
		
		public ExtensionEvent.ExtensionEventBuilder getOrCreateExtensionEvent(int _index) {
		
			if (extensionEvent==null) {
				this.extensionEvent = new ArrayList<>();
			}
			ExtensionEvent.ExtensionEventBuilder result;
			return getIndex(extensionEvent, _index, () -> {
						ExtensionEvent.ExtensionEventBuilder newExtensionEvent = ExtensionEvent.builder();
						return newExtensionEvent;
					});
		}
		
	
		@Override
		public ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder addExtensionEvent(ExtensionEvent extensionEvent) {
			if (extensionEvent!=null) this.extensionEvent.add(extensionEvent.toBuilder());
			return this;
		}
		
		@Override
		public ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder addExtensionEvent(ExtensionEvent extensionEvent, int _idx) {
			getIndex(this.extensionEvent, _idx, () -> extensionEvent.toBuilder());
			return this;
		}
		@Override 
		public ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder addExtensionEvent(List<? extends ExtensionEvent> extensionEvents) {
			if (extensionEvents != null) {
				for (ExtensionEvent toAdd : extensionEvents) {
					this.extensionEvent.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("extensionEvent")
		public ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder setExtensionEvent(List<? extends ExtensionEvent> extensionEvents) {
			if (extensionEvents == null)  {
				this.extensionEvent = new ArrayList<>();
			}
			else {
				this.extensionEvent = extensionEvents.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public ExtendibleProvisionAdjustedDates build() {
			return new ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesImpl(this);
		}
		
		@Override
		public ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder prune() {
			extensionEvent = extensionEvent.stream().filter(b->b!=null).<ExtensionEvent.ExtensionEventBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getExtensionEvent()!=null && getExtensionEvent().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder o = (ExtendibleProvisionAdjustedDates.ExtendibleProvisionAdjustedDatesBuilder) other;
			
			merger.mergeRosetta(getExtensionEvent(), o.getExtensionEvent(), this::getOrCreateExtensionEvent);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ExtendibleProvisionAdjustedDates _that = getType().cast(o);
		
			if (!ListEquals.listEquals(extensionEvent, _that.getExtensionEvent())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (extensionEvent != null ? extensionEvent.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExtendibleProvisionAdjustedDatesBuilder {" +
				"extensionEvent=" + this.extensionEvent +
			'}';
		}
	}
}
